package com.leeue.queue.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者
 *
 * @author liyue
 * @date 2019/12/6 11:42
 */
public class QueueConsumer {

    /**
     * 这个是MQ 的通讯地址，不是 mq 的页面地址 ，会自己创建一个端口 去和mq进行通讯 端口是: 61616
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

        System.out.println("我是消费者");

        //4.创建会话，参数1 设置是否需要以实物方式进行提交，参数2 消息接收方式 这里设置成自动签收
        final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //5.创建需要获取到的队列 目标
        Queue queue = session.createQueue(MY_QUEUE);

        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        //7.创建监听，消费消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //转换成文本消息格式
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println("消费者消费消息:消息内容=" + textMessage.getText());
                    //手动签收消息，告诉消息中间件该消息已经被消费了。

                    //textMessage.acknowledge(); //手动签收

                    session.commit(); // 以事务的方式进行提交。说明消息被消费了。

                } catch (JMSException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        //System.out.println("队列消息消费完毕!");

        //9.消费者是 一直跟 mq 保持长连接的 不会端开 如果断开了，消息会产生堆积
        // connection.close();

    }
}
