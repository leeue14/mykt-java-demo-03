package com.leeue.connection.foctory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 封装RabbitMQ连接
 *
 * @author liyue
 * @date 2019/12/10 18:38
 */
public class MQConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置mq地址
        connectionFactory.setHost("120.78.185.72");
        //设置通讯协议端口号amqp 协议
        connectionFactory.setPort(5672);
        //设置需要访问的vhost
        connectionFactory.setVirtualHost("/test01_host");
        //设置用户名
        connectionFactory.setUsername("admin");
        //设置密码
        connectionFactory.setPassword("admin");

        return connectionFactory.newConnection();
    }
}
