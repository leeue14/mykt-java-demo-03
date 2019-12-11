package com.leeue.topic.producer;

import com.leeue.foctory.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Rabbit 发布和订阅 交换类型: type = fanout 类型 : 只要消费者绑定了我这个交换机，队列都会把消息投递给消费者
 * <p>
 * 生产者
 * </p>
 *
 * @author liyue
 * @date 2019/12/11 14:20
 */
public class TopicProducer {

    private static final String DESTINATION_NAME = "my_topic_destination";

    public static void main(String[] args) throws IOException, TimeoutException {

        //1.建立mq连接
        Connection connection = MQConnectionUtils.getConnection();

        //2.创建通道
        Channel channel = connection.createChannel();

        //3.生产者绑定交换机
        channel.exchangeDeclare(DESTINATION_NAME, "topic");

        //4.创建队列消息
        String msg = "my_topic_msg";

        //定义转发规则 routingKey
        //   String routingKey = "log.email.sms";

        String routingKey = "log.email";


        for (int i = 10; i < 20; i++) {

            channel.basicPublish(DESTINATION_NAME, routingKey, null, (msg + i).getBytes());
        }

        System.out.println("生产者投递消息完成");

        //关闭通道和关闭连接
        channel.close();
        connection.close();
    }
}
