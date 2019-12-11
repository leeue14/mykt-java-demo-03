package com.leeue.topic.consumer;

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
public class TopicSmsConsumer {

    private static final String SMS_QUEUE = "sms_queue_topic";

    private static final String DESTINATION_NAME = "my_topic_destination";

    public static void main(String[] args) throws IOException, TimeoutException {

        //1.建立mq连接
        Connection connection = MQConnectionUtils.getConnection();

        //2.创建通道
        Channel channel = connection.createChannel();

        //3.生产者绑定交换机
        channel.queueDeclare(SMS_QUEUE, false, false, false, null);

        //定义转发规则 routingKey 消费者设置为 * 匹配log.后面任意字符的路由策略
        String routingKey = "log.*";

        //4.消费者绑定交换机
        channel.queueBind(SMS_QUEUE, DESTINATION_NAME, routingKey);

        System.out.println("短信消费者已启动");

        //5.消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body, "UTF-8");

                System.out.println("邮件消费者开始消费消息=" + msg);
            }
        };

        //手动签收
        channel.basicConsume(SMS_QUEUE, true, defaultConsumer);
    }
}
