

/**
 * @aauthor 制冷
 * @date 2023/2/10 -- 17:22
 * @aversion 1.0
 * 保存的数据
 */
public class Data {
    static String ip ;//mirai的IP地址
    static String port;//端口号
    static String QQID;//机器人QQ号
    static String masterID;//管理员QQ号
    static String verifyKey;//当前机器人的verifyKey
    static String sessionkey;//获取到的session key
    static String url; //链接
    static String xiaoxichangdu = "0";//消息缓存长度
    static String xiaoxi;//获取的消息
    static String  gong;//是否公用

    public static String toStrings() {
        return "{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", QQID='" + QQID + '\'' +
                ", masterID='" + masterID + '\'' +
                ", verifyKey='" + verifyKey + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
