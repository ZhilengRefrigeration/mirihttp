
import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
/**
 * @aauthor 制冷
 * @date 2023/2/10 -- 17:46
 * @aversion 1.0
 * 读取配置信息到DATA
 */
public class Wenjian {
    /***********【该方法用于判断配置文件是否存在，如果存在则读取，不存在则创建】*************/
    public void MAin() throws IOException {
        File file = new File("config.yml");
        if (file.exists() == false) {
            Map<String, Object> map = new HashMap<>();
            map.put("ip", "127.0.0.1");
            map.put("port", "8080");
            map.put("QQID","123456789");
            map.put("verifyKey","1234567890");
            map.put("masterID","123456789");
            map.put("gong","true");
            Yaml yml = new Yaml();
            FileWriter writer = new FileWriter("config.yml", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.newLine();
            yml.dump(map, buffer);
            buffer.close();
            writer.close();
        }
        Yaml yml = new Yaml();
        FileReader reader = new FileReader("config.yml");
        BufferedReader buffer = new BufferedReader(reader);
        Map<String,Object> map = (Map<String, Object>) yml.load(buffer);
        Data.ip = (String) map.get("ip");
        Data.port = (String) map.get("port");
        Data.QQID = (String) map.get("QQID");
        Data.verifyKey = (String) map.get("verifyKey");
        Data.masterID = (String) map.get("masterID");
        Data.gong = (String) map.get("gong");

        Data.url ="http://" + (String) map.get("ip") +":"+ (String) map.get("port");
        buffer.close();
        reader.close();
    }

}

