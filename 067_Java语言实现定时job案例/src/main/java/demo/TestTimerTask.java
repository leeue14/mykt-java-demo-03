package demo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 演示:使用TimerTask演示定时任务 这个是Javadi.util自带的一个定时器 只适合客户端使用 不适合服务器端
 * 定义时间方式太简单了。
 * @author liyue
 * @date 2019/9/5 11:51
 */
public class TestTimerTask {

    private static int count = 0;

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("count:"+count++);
            }
        };

        //定义触发规则
        Timer timer = new Timer();
        //天数  这个是表示几天后
        long delay = 0;
        //秒数 每隔几秒触发一次
        long period = 1000;

        timer.schedule(timerTask,0,1000);
    }
}
