package demo;

/**
 * 使用线程来实现定时job
 * @author liyue
 * @date 2019/9/5 11:45
 */
public class TestThread {
    private static  int count;

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    //每个一秒执行一次代码
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    System.out.println("count:"+count);
                }
            }
        };

        new Thread(runnable).start();

    }
}
