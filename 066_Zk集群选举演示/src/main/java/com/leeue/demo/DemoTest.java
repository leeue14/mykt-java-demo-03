package com.leeue.demo;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author liyue
 * @date 2019/9/4 17:52
 */
public class DemoTest {

    public static void main(String[] args) {
        //zk集群环境下连接 客户端连接直接把这个 集群地址copy过去就行了 SpringBoot    上也是这样
        String connection = "192.168.2.5:2181,192.168.2.7:2181,192.168.2.185:2181";

        ZkClient zkClient = new ZkClient(connection);

        zkClient.createPersistent("/leeuezkdemo_jiqundizhi");

        zkClient.close();

    }
}
