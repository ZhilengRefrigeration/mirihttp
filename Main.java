

/**
 * @aauthor 制冷
 * @date 2023/2/10 -- 17:10
 * @aversion 1.0
 * 交流群：467021837  开发者QQ：3377658377
 * 程序入口
 */
public class Main {
    public static void main(String[] args) throws Exception{
        //读取配置信息
        System.out.println("\033[32m \n" +
                "       　  　▃▆█▇▄▖\n" +
                "　 　 　 ▟◤▖　　　◥█▎\n" +
                "   　 ◢◤　 ▐　　　 　▐▉\n" +
                "　 ▗◤　　　▂　▗▖　　▕█▎\n" +
                "　◤　▗▅▖◥▄　▀◣　　█▊\n" +
                "▐　▕▎◥▖◣◤　　　　◢██\n" +
                "█◣　◥▅█▀　　　　▐██◤\n" +
                "▐█▙▂　　     　◢██◤\n" +
                "◥██◣　　　　◢▄◤\n" +
                " 　　▀██▅▇▀\n" +
                "哲学♂♂ 114514\n\033[0m"
                );
        System.out.println("\033[32m 读取配置文件ing..... \033[0m");
        Wenjian wenjian = new Wenjian();
        wenjian.MAin();
        System.out.println("\033[32m 配置文件读取成功，您设置的miraittp:" + Data.toStrings() + "\n" + "开始连接mirai并初始化ing...... \033[0m ");
        //初始化
        Qingqiu qingqiu = new Qingqiu();
        Json json = new Json();
        Chushihua chushihua = new Chushihua();
        chushihua.Main(qingqiu,json);

        //初始化成功，给主人发送提示
        System.out.println("\033[32m初始化成功,开始等待指令\n \033[0m");
        qingqiu.Post(Data.url + "/sendFriendMessage",json.getWenbenxiaoxi(Data.masterID,"初始化成功,开始等待指令"));


        //开始计时
        Jishhih jishhih = new Jishhih(chushihua, qingqiu, json);
        jishhih.start();

        //监听消息
        Jianting jianting = new Jianting(qingqiu, json);
        jianting.start();
    }
}
