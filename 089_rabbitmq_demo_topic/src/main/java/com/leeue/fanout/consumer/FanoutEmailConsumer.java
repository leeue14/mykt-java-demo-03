package com.leeue.fanout.consumer;

import com.leeue.foctory.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 消费者
 *
 * @author liyue
 * @date 2019/12/11 14:31
 */
public class FanoutEmailConsumer {

    private static final String EMAIL_QUEUE = "email_queue_fanout";

    private static final String DESTINATION_NAME = "my_fanout_destination";

    public static void main(String[] args) throws IOException, TimeoutException {

        //1.建立mq连接
        Connection connection = MQConnectionUtils.getConnection();

        //2.创建通道
        Channel channel = connection.createChannel();

        //3.生产者绑定交换机
        channel.queueDeclare(EMAIL_QUEUE, false, false, false, null);

        //4.消费者绑定交换机
        channel.queueBind(EMAIL_QUEUE, DESTINATION_NAME, "");

        //5.消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body, "UTF-8");

                System.out.println("邮件消费者开始消费消息=" + msg);
            }
        };

        //手动签收
        channel.basicConsume(EMAIL_QUEUE, true, defaultConsumer);
    }
}
