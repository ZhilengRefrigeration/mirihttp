

/**
 * @aauthor 制冷
 * @date 2023/2/10 -- 17:55
 * @aversion 1.0
 * 计时线程，用于更新sessionkey
 */
public class Jishhih extends Thread{
    Chushihua chushihua =null;
    Qingqiu qingqiu = null;
    Json json =null;
    long startTime ;//计时器

    public Jishhih(Chushihua chushihua, Qingqiu qingqiu, Json json) {
        this.chushihua = chushihua;
        this.qingqiu = qingqiu;
        this.json = json;
    }

    @Override
    public void run() {
    //获取时间
        startTime = System.currentTimeMillis();
        while (true){
            //每隔五秒看一次需不需要更新sessionkey
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断时间过了多久，如果超过了25分钟就更换sessionkey
            if((System.currentTimeMillis() / 1000 - this.startTime / 1000) > 60*25){
                //释放原来的sessionkey
                try {

                    System.out  .println("\033[32m 更换key...ing \033[0m"+(System.currentTimeMillis()*this.startTime/1000));
                    qingqiu.Post(Data.url + "/release",json.getShifang());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //获取新的sessionkey并绑定
                try {
                    chushihua.Main(qingqiu,json);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //重置计时器
                startTime = System.currentTimeMillis();
            }
        }
    }
}
