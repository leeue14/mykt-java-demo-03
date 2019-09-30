package com.leeue.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 这个类项目启动之后就开始执行这个方法  启动完成之后执行这个方法  重写了ApplicationRunner<br>
 * ctrl+alt+t 显示快捷键  try-catch
 * </p>
 *
 * @author liyue
 * @date 2019/9/4 14:16
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    private static final String CONNECTING = "120.78.185.72:2181";
    private static final int SESSION_TIME_OUT = 5000;
    //获取zk连接
    protected ZkClient zkClient = new ZkClient(CONNECTING, SESSION_TIME_OUT, 10000);
    //需要创建锁的路径
    protected String path = "/election";

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //1.项目启动的时候会在zk上创建一个相同的临时节点
        createEphemeral();
        //2.那个项目创建成果，谁就是主服务器
        //3.使用服务器监听节点是否被删除，如果接受到节点被删除的话吗，重新开始选举(重新开始创建节点)
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }
            //节点如果被删除后会发出删除的通知
            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("原来的主节点挂了，我们开始重新进行选举策略....");
                createEphemeral();
            }
        });
    }

    /**
     * 创建临时节点
     *
     * @return
     */
    private void createEphemeral() {

        try {
            zkClient.createEphemeral(path);
            System.out.println("severPort:" + serverPort + ",选举成功.....");
            ElectionMaster.isSurvival = true;
        } catch (Exception e) {
            System.out.println("该节点已经存在");
            ElectionMaster.isSurvival = false;
        }
    }
}
