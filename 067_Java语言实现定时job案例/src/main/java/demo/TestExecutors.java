package demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池方式来实现
 *
 * @author liyue
 * @date 2019/9/5 11:57
 */
public class TestExecutors {
    private static int count;

    public static void main(String[] args) {
        //使用线程池方式来实现
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                count++;
                System.out.println("count:" + count);
            }
        };

        //第二个参数为首次执行的延迟的时间，第三个三处为定时执行的时间间隔
        scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);

    }
}
