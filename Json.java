

/**
 * @aauthor 制冷
 * @date 2023/2/10 -- 17:11
 * @aversion 1.0
 * 保存miraihttp的Json参数
 */
public class Json {
    String verifyKey;//获取sessionkey的参数
    String bangding;//绑定sessionkey与QQ号的参数
    String wenbenxiaoxi;//用于发送文本消息的参数
    String shifang;//释放sessionkey的参数
    String huoquxiaoxi;//获取消息的参数
    String qunwenben;//用于发送群消息参数
    String chaxun;//用于查询的参数

/***********【get方法】*************/
    public String getVerifyKey() {
        return "{\"verifyKey\":\"" + Data.verifyKey + "\"}";
    }

    public String getBangding() {
        return "{" +
                "\"sessionKey\": \""+Data.sessionkey+"\"," +
                "\"qq\": "+Data.QQID+"" +
                "}";
    }

    public String getWenbenxiaoxi(String QQID,String text) {
        return "{" +
                "\"sessionKey\":\""+Data.sessionkey+"\"," +
                "\"target\":"+QQID+"," +
                "\"messageChain\":[" +
                "{ \"type\":\"Plain\", \"text\":\""+text+"\" }" +
                "]" +
                "}";
    }

    public String getShifang() {
        return "{" +
                "\"sessionKey\": \""+Data.sessionkey+"\"," +
                "\"qq\": "+Data.QQID+"" +
                "}";
    }

    public String getHuoquxiaoxi() {
        return huoquxiaoxi;
    }

    public String getQunwenben(String qunID,String text) {
        return "{" +
                "\"sessionKey\":\""+Data.sessionkey+"\"," +
                "\"target\":"+qunID+"," +
                "\"messageChain\":[" +
                "{ \"type\":\"Plain\", \"text\":\""+text+"\" }]" +
                "}";
    }

    public String getChaxun(String QQID) {
        return "{\"Q\":\"" + ""+QQID+"" + "\"}";
    }
}
