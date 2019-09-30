package com.leeue.zk;


import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


/**
 * 演示: 使用Java语言操作zk代码
 * 开启事件通知、增加节点等
 * @author liyue
 * @date 2019/8/27 10:53
 */
public class Test001 {
    private static final String CONNECTING = "120.78.185.72:2181";
    private static final int SESSION_TIME_OUT = 5000;

    //使用Javabing并发包，信号量技术，控制zk连接成功之后，开始创建节点，否则情况下会等待。这里的1表示开始置为1
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        /**
         第一个参数是连接地址
         第二个参数是超时时间
         第三个参数是 监听事件通知的
         */
        //1.创建一个zk连接
        ZooKeeper zk = new ZooKeeper(CONNECTING, SESSION_TIME_OUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //可以监听zk节点是否变化  如:连接zk成功,增加节点成功

                Event.KeeperState state = watchedEvent.getState();

                Event.EventType type = watchedEvent.getType();

                if (Event.KeeperState.SyncConnected == state) {
                    if (Event.EventType.None == type) {
                        System.out.println("zk连接成功");
                        countDownLatch.countDown(); //一旦调用这个方法适合，就会减一
                    }

                    if (Event.EventType.NodeCreated == type) {
                        System.out.println("事件通知:创建节点成功" + type.name());
                    }
                }


            }
        });
        // ZooDefs.Ids.OPEN_ACL_UNSAFE 设置权限的，任何人都是可以访问的，不需要认证的。
        /**
         四种节点类型:
         CreateMode.EPHEMERAL : 创建一个临时节点
         CreateMode.EPHEMERAL_SEQUENTIAL :临时节点，如果节点发生重复的情况下，会自动增长id,保证唯一性
         CreateMode.PERSISTENT :永久节点
         CreateMode.PERSISTENT_SEQUENTIAL : 永久节点，如果节点发生重复的情况下，会自动增长id,保证唯一性
         */
        //如果计数器不为 0 就会一直在这阻塞，一直等待。
        countDownLatch.await();
        //开启当前节点的事件通知 将这个节点设置为true
        zk.exists("/test", true);
        //2.创建zk节点
        String nodeResult = zk.create("/test", "我是测试节点内容".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点名称=" + nodeResult);

        //先创建父节点，再创建子节点
        String nodeResult1 = zk.create("/test-f", "我是父节点".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点名称=" + nodeResult1);

        String nodeResult2 = zk.create("/test-f/test001", "我是子节点".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点名称=" + nodeResult2);

        zk.close();
    }
}
