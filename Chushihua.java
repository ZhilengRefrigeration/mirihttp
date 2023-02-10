
import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @aauthor 制冷
 * @date 2023/2/10 -- 17:16
 * @aversion 1.0
 * 初始化miraihttp,即获取sessionkey，绑定sessionkey
 */
public class Chushihua {
    public void Main(Qingqiu  qingqiu,Json json) throws Exception {
        String str = null;//用于保存返回值
        //获取sessionkey,
        try {
            str = qingqiu.Post(Data.url + "/verify",json.getVerifyKey());
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,"获取sessionkey错误，可能是mirai框架未启动，或者配置文件ip或者端口错误！！");//提示错误
        }
        //利用正则表达式判断code的值是不是0，因为miraihtt的返回值code为0则正常，否则不正常
        Pattern pattern = Pattern.compile("\"code\":(\\d+)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String codeValue = matcher.group(1);
            if ("0".equals(codeValue)) {
                //从返回值中提取sessionkey保存到Data.sessionkey
                Data.sessionkey = str.substring(21,29);
                //绑定sessionkey与机器人QQ号
                try {
                    qingqiu.Post(Data.url + "/bind",json.getBangding());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null,"无法绑定机器人QQ，请与开发者联系！！");//提示错误
                }
            } else {
                JOptionPane.showMessageDialog(null,"你的配置文件可能没有配置，可能是verifyKey设置错误！！");//提示错误
                System.exit(0);//检测到配置错误关闭程序

            }
        }

    }
}
