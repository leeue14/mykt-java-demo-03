package com.leeue.zk;

import com.leeue.OrderNumGenerator;

/**
 * @author liyue
 * @date 2019/8/28 15:27
 */
public class TestMain implements Runnable {
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    private AbstractZKLock zkLock = new ZkDistrbuteLock();

    @Override
    public void run() {
        getNumber();
    }

    private void getNumber() {
        try {
            zkLock.getLock();
            String num = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + "-number=" + num);
        } catch (Exception e) {

        } finally {
            zkLock.unLock();
        }

    }

    public static void main(String[] args) {
        //使用ZK分布锁的时候，这个不能是全局的，因为只创建了一个zk连接，不是分布式
      //  TestMain testMain = new TestMain();
        for (int i = 0; i < 1000; i++) {
            //模拟分布式，多个请求连接到zk上  强制关闭客户端zk上删除锁会有延迟
            TestMain testMain = new TestMain();
            new Thread(testMain).start();
        }
    }
}
