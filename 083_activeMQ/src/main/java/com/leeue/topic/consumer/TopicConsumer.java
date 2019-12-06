package com.leeue.topic.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * <p>
 * 消费者： 发布订阅模式消费者必须要先启用。才能接受到 生产者发送过来的消息
 * </p>
 *
 * @author liyue
 * @date 2019/12/6 11:42
 */
public class TopicConsumer {

    /**
     * 这个是activeMQ 的通讯地址，不是 activeMQ 的页面地址 ，会自己创建一个端口 去和mq进行通讯 端口是: 61616
     */
    private static final String MQ_URL = "tcp://127.0.0.1:61616";

    private static final String MY_TOPIC = "my_topic";

    public static void main(String[] args) throws JMSException {

        //1.创建连接工厂 密码采用默认密码 admin admin
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);

        //2.创建连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //3启动连接
        connection.start();

        System.out.println("发布订阅模式接受消息");

        //4.创建会话，参数1 设置是否需要以实物方式进行提交，参数2 消息接收方式 这里设置成自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建需要获取到的队列 目标
        Topic topic = session.createTopic(MY_TOPIC);

        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(topic);

        //7.创建监听，消费消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //转换成文本消息格式
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println("消费者消费消息:消息内容=" + textMessage.getText());
                } catch (JMSException e) {
                    System.out.println(e.getMessage());
                    ;
                }
            }
        });

        //System.out.println("队列消息消费完毕!");

        //9.消费者是 一直跟 mq 保持长连接的 不会端开 如果断开了，消息会产生堆积
        // connection.close();

    }
}
