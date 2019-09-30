package com.leeue.zk;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * zk锁:父类用于抽象一些共同的代码   模板方式
 * @author liyue
 * @date 2019/8/28 14:53
 */
public abstract class AbstractZKLock {

    private static final String CONNECTING = "120.78.185.72:2181";
    private static final int SESSION_TIME_OUT = 5000;
    //获取zk连接
    protected ZkClient zkClient = new ZkClient(CONNECTING,SESSION_TIME_OUT,10000);
    //需要创建锁的路径
    protected  String lockPath = "/lockPath";

    protected CountDownLatch countDownLatch = null;
    //获取zk锁
    void getLock(){
        //1.判断是否能获取到锁，如果获取到锁就成功了
        if(tryLock()){
            System.out.println("创建锁成功,并执行需要的业务逻辑");
        }else {
            //等待获取锁
            waitLock();
            //如果锁被释放了，就进行抢锁。(使用的是递归)
            getLock();
        }
        //如果存在
        //2.如果当前锁已经被创建，就等待，如果当前锁不存在，就创建一个临时节点，创建成功后执行业务逻辑，执行完成后就释放锁
        //

    }

    //尝试获取锁
    abstract  boolean tryLock();

    //等待获取锁
    abstract void waitLock() ;



    //释放zk锁
    void unLock(){
        if(zkClient != null){
            zkClient.close();
            System.out.println("释放zk锁完毕!");
        }
    }
}
