package com.leeue.zk;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author liyue
 * @date 2019/8/28 15:08
 */
public class ZkDistrbuteLock extends AbstractZKLock {


    //尝试获取锁
    @Override
    boolean tryLock() {
        try {
            //如果节点不存在
            if (!zkClient.exists(lockPath)) {
                //创建临时节点
                zkClient.createEphemeral(lockPath);
                System.out.println("创建临时节点获取到锁");
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    void waitLock() {
        //监听事件通知
        IZkDataListener iZkDataListener = new IZkDataListener() {
            //监听数据被改变
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }
            //监听数据被删除
            @Override
            public void handleDataDeleted(String s) throws Exception {
                //说明锁被释放，计数器-- 不需要再等待
                if(countDownLatch != null){
                    countDownLatch.countDown();
                }

            }
        };
        //注册事件通知
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);
        //是用countDownLatch来进行控制等待
        countDownLatch = new CountDownLatch(1);
        try {
            //等待
            countDownLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //后面代码是为了不影响程序的效率，建议删除该事件监听 因为这个代码会一直在执行的，而每次都会创建一个事件监听，会导致服务器多次监听。
        zkClient.unsubscribeDataChanges(lockPath,iZkDataListener);

    }
}
