package com.leeue.customer;

import com.leeue.connection.foctory.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 消费者 工作消费模式 公平性队列
 *
 * @author liyue
 * @date 2019/12/10 18:48
 */
public class Customer2 {

    private static final String QUEUE_NAME = "leeue_rabbitmq_demo_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = MQConnectionUtils.getConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //设置工作性队列，每次只取一个消息，每个消息要等消费完成后，才继续消费下一个消息
        channel.basicQos(1);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

            //时刻监听，消息队列里，是否有消息进来，然后进行消费。
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);

                String msg = new String(body, "UTF-8");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("消费者获取消息:" + msg);
                //手动签收消息，multiple:表示签收所有的消息
                /***
                 * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
                 * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
                 * basicAck 方法的第二个参数 multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认；如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认。
                 * （批量确认针对的是整个信道，参考gordon.study.rabbitmq.ack.TestBatchAckInOneChannel.java。）
                 */
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        };
        // 3.设置消费者应答模式，如果为autoAck = true的情况下，表示设置为自动应答模式
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);

        // channel.close();
        // connection.close();
    }
}
