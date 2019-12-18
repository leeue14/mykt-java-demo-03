package leeue.amqp.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import leeue.amqp.foctory.MQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 生产者
 * 消息模式:点对点 简单的队列
 *
 * @author liyue
 * @date 2019/12/10 18:43
 */
public class Producer {

    private static final String QUEUE_NAME = "leeue_rabbitmq_demo_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = MQConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        /**
         *创建队列
         * durable:是否持久化，
         * exclusive: 为true的时候，当这个连接断了，队列里面的消息就会被删除
         * autoDelete: 自动删除，当这个队列里面的消息很久没使用过的消息
         * arguments: 其他属性
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String msg = "test_msg";

        try {
            //开启事务
            channel.txSelect();
            //将消息推送到消息中间件中。
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

            System.out.println("消息已被投递");

            //异常//如果此时报异常了，生产者会不会把消息推送到消息队列中去。
            int i = 1 / 0;

            channel.txCommit();//提交事务

        } catch (IOException e) {
            e.printStackTrace();
            channel.txRollback();
        } finally {
            //关闭通道
            channel.close();
            //关闭连接
            connection.close();
        }
    }
}