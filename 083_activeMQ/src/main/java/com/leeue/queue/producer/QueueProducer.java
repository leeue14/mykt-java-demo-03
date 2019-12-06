package com.leeue.queue.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者
 *
 * @author liyue
 * @date 2019/12/6 11:42
 */
public class QueueProducer {

    /**
     * 这个是activeMQ 的通讯地址，不是 activeMQ 的页面地址 ，会自己创建一个端口 去和mq进行通讯 端口是: 61616
     */
    private static final String MQ_URL = "tcp://127.0.0.1:61616";

    private static final String MY_QUEUE = "my_queue";

    public static void main(String[] args) throws JMSException {

        //1.创建连接工厂 密码采用默认密码 admin admin
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);

        //2.创建连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //3启动连接
        connection.start();

        //4.创建会话，参数1 设置是否需要以实物方式进行提交，参数2 消息接收方式 这里设置成自动签收

       /* static final int AUTO_ACKNOWLEDGE = 1; 自动签收

        static final int CLIENT_ACKNOWLEDGE = 2; 手动签收

        static final int DUPS_OK_ACKNOWLEDGE = 3;

        static final int SESSION_TRANSACTED = 0;*/


        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //5.创建队列 目标
        Queue queue = session.createQueue(MY_QUEUE);

        //6.创建生产者
        MessageProducer producer = session.createProducer(queue);

        //对消息进行持久化 只有设置了这个消息才会进行持久化到硬盘上，否则ActiveMQ挂了后，消息就会丢失
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        //7.创建消息
        for (int i = 0; i < 10; i++) {

            TextMessage textMessage = session.createTextMessage("消息内容:" + i);

            System.out.println(textMessage.getText());
            //8.发送消息
            producer.send(textMessage);

            //开启事务的方式进行提交
            session.commit();
        }

        System.out.println("10条消息发送完毕!");

        //9.关闭连接
        connection.close();

    }
}
