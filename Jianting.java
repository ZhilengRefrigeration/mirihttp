
import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @aauthor 制冷
 * @date 2023/2/10 -- 18:25
 * @aversion 1.0
 * 用于监听框架消息
 */
public class Jianting extends Thread{
    Qingqiu qingqiu;
    Json json;
    String str;//用于接收返回值方便处理
    String leng;//未读消息长度
    String groupId ;//群号

    public Jianting(Qingqiu qingqiu, Json json) {
        this.qingqiu = qingqiu;
        this.json = json;
    }

    @Override
    public void run() {
        //每秒获取未读长度，如果大于0则获取消息
        while (true){
            //休眠一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取未读消息长度的返回值
            try {
                str = qingqiu.Get(Data.url+"/countMessage?sessionKey="+Data.sessionkey);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,"获取消息长度错误，请联系开发人员！！");//提示错误
                exception.printStackTrace();System.exit(0);//检测到配置错误关闭程序
            }
            //从返回值获取未读消息的长度
            Pattern pattern = Pattern.compile("(?<=\"data\":)(.*?)(?=})");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                leng = matcher.group(1);
                //如果未读消息为0就不获取未读消息，跳过本次循环
                if(leng.equalsIgnoreCase("0"))continue;
            }
            //获取未读消息具体信息
            try {
                str = qingqiu.Get(Data.url+"/fetchMessage?sessionKey="+Data.sessionkey+"&count="+leng);
                //获取发送消息的群号
                String pattern2 = "\"group\":\\{\"id\":(\\d+),";
                Pattern r = Pattern.compile(pattern2);
                Matcher m = r.matcher(str);
                if(m.find()) {
                    groupId = m.group(1);
                }
                //获取群号

                String patternj = "\"name\":\"(.+?)\"";
                Pattern rj = Pattern.compile(patternj);
                Matcher mj = rj.matcher(str);
                if (mj.find()) {
                }
                //获取消息
                String patternq = "\"text\":\"(.+?)\"";
                Pattern rq = Pattern.compile(patternq);
                Matcher mq = rq.matcher(str);
                if (mq.find()) {
                    System.out.println("\033[36m "+"群："+"["+mj.group(1)+"]"+groupId+" 消息："+mq.group(1)+" \033[0m");
                }
            } catch (Exception exception) {
                System.out.println("\033[36m "+"收到私聊消息，不处理"+" \033[0m");
            }
            //判断消息是不是主人发的,是不是指令
            String regex = "\"text\":\"(查询.*?)\"";
            Pattern pattern0 = Pattern.compile(regex);
            Matcher matcher0 = pattern0.matcher(str);
            if (matcher0.find()) {
                Pattern patterns = Pattern.compile("\"id\":(\\d+),");
                Matcher matchers = patterns.matcher(str);
                while (matchers.find()) {
                    if (Data.gong.equalsIgnoreCase("true") || matchers.group(1).equals(Data.masterID)) {
                        //查询数据库有没有对应QQ号
                        try {
                            str = qingqiu.Post ("http://www.shenyuge.top:8520/api/phone/",json.getChaxun(matcher0.group(1).substring(2)));
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null,"api连接失败，请联系开发人员！！");//提示错误
                            exception.printStackTrace();
                        }
                        //如果数据库没有对应的QQ号
                            if(str.equalsIgnoreCase("{\"data\":[],\"success\":true}")){
                                try {
                                    //发送群消息
                                    qingqiu.Post(Data.url+"/sendGroupMessage",json.getQunwenben(groupId, "该手机号没有被泄露"));
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }}else{
                                    //如果有QQ号就提取出对应手机号
                                String pattern1 = "\"phone\":\"(\\d+)\"";
                                Pattern r0 = Pattern.compile(pattern1);
                                Matcher m0 = r0.matcher(str);
                                if (m0.find()) {
                                    String phone= m0.group(1);
                                try {
                                    //发送群消息
                                    qingqiu.Post(Data.url+"/sendGroupMessage",json.getQunwenben(groupId, "查询成功："+phone));
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                             }
                            }
                    }
                }
            }
        }
    }
}
