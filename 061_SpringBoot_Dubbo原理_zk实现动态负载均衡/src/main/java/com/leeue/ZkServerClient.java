package com.leeue;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ZkServerClient {
    //存放在服务端做集群的地址
    public static List<String> listServer = new ArrayList<String>();

    private static final String CONNECTING = "120.78.185.72:2181";
    private static final int SESSION_TIME_OUT = 5000;

    public static void main(String[] args) {
        initServer();
        ZkServerClient client = new ZkServerClient();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name;
            try {
                name = console.readLine();
                if ("exit".equals(name)) {
                    System.exit(0);
                }
                client.send(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取注册中心的 注册的
    public static void initServer() {
        listServer.clear();
        //listServer.add("127.0.0.1:18080");
        //1.建立zk连接
        ZkClient zkClient = new ZkClient(CONNECTING, SESSION_TIME_OUT, 10000);
        //2.读取注册父节点信息
        String root = "/demo_service";
        //1.获取到当前父节点下面的子节点。
        List<String> childrenList = zkClient.getChildren(root);
        for (String p : childrenList) {
            String path = root + "/" + p;
            String value = zkClient.readData(path);
            listServer.add(value);
        }
        System.out.println("服务发现成功:" + listServer.toString());

        //使用事件监听，如果服务宕机情况下，会重新读取新的节点
        zkClient.subscribeChildChanges(root, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("有服务发生改变，重新获取获取服务");
                listServer.clear();
                for (String p : childrenList) {
                    String path = root + "/" + p;
                    String value = zkClient.readData(path);
                    listServer.add(value);
                }
            }
        });

    }

    private static int reqCount = 1;

    private static int servetCout = 0;

    // 获取当前server信息  Doubble 底层原理就是这样  使用ZK做负载均衡
    public static String getServer() {
        if (listServer != null) {
            servetCout = listServer.size();
        }
        //本地轮训算法
        int i = reqCount % servetCout;
        reqCount++;
        return listServer.get(i);
    }

    public void send(String name) {

        String server = ZkServerClient.getServer();
        String[] cfg = server.split(":");

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(cfg[0], Integer.parseInt(cfg[1]));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(name);
            while (true) {
                String resp = in.readLine();
                if (resp == null)
                    break;
                else if (resp.length() > 0) {
                    System.out.println("Receive : " + resp);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
