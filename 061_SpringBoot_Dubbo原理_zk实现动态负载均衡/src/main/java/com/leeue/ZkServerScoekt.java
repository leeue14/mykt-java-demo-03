package com.leeue;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//##ServerScoekt服务端
public class ZkServerScoekt implements Runnable {

    private static final String CONNECTING = "120.78.185.72:2181";
    private static final int SESSION_TIME_OUT = 5000;

    private static int port = 18080;

    public static void main(String[] args) throws IOException {

        ZkServerScoekt server = new ZkServerScoekt(port);
        Thread thread = new Thread(server);
        thread.start();
    }

    public ZkServerScoekt(int port) {
        this.port = port;
    }

    //注册服务:起来有几个服务端，就会有几个服务被注册到zk上去，然后服务端就会进行调用。

    public void regServer() {
        //1.建立zk连接
        ZkClient zkClient = new ZkClient(CONNECTING, SESSION_TIME_OUT, 10000);
        String root = "/demo_service";
        //2.创建父节点 父节点可以是临时可以是持久
        if (!zkClient.exists(root)) {
            //如果当前创建的父节点不存在，就会创建
            zkClient.createPersistent(root);
        }
        //3.创建子节点 子节点要是临时节点
        String node = root + "/service_" + port;
        String nodeValue = "127.0.0.1:" + port;
        if (!zkClient.exists(node)) {
            //判断当前子节点是否存在，如果存在就删除了再创建。更新这个子节点
            zkClient.delete(node);
        }
        //创建子节点 临时的
        zkClient.createEphemeral(node, nodeValue);
        System.out.println("服务注册成功:" + nodeValue);
    }


    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            //在启动服务的时候，进行注册服务
            regServer();
            System.out.println("Server start port:" + port);

            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e2) {

            }
        }
    }

}
