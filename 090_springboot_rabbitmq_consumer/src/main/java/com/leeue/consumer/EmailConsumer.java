package com.leeue.consumer;

import com.alibaba.fastjson.JSONObject;
import com.leeue.utils.HttpClientUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 消费者
 * </p>
 *
 * @author liyue
 * @date 2019/12/12 10:53
 */
@Component
public class EmailConsumer {

    @RabbitListener(queues = "fanout_email_queue")
    public void process(Message msg, @Headers Map<String, Object> hearders, Channel channel) throws Exception {

        System.out.println("邮件消费者获取生产者的消息:" + msg);

        //1.获取消息id
        String messageId = msg.getMessageProperties().getMessageId();

        System.out.println("获取到消息ID:" + messageId + "获取到消息:" + new String(msg.getBody(), "UTF-8"));

        //实验生产者 出现异常，rabbitMQ的补偿重试机制
        //  int i = 1 / 0;

        JSONObject jsonObject = JSONObject.parseObject(new String(msg.getBody(), "UTF-8"));

        String emaiUrl = "http://127.0.0.1:8140/sendEmail?email=" + jsonObject.getString("email");

        JSONObject result = HttpClientUtils.httpGet(emaiUrl);

        System.out.println(result);
        //如何调用第三方邮件接口无法访问，如何实现自动重试

        if (result == null) {
            //RabbitMQ在程序抛出异常的时候会自动进行重试
            throw new Exception("调用发送邮件接口失败");
        }
        System.out.println("返回结果:" + result.toJSONString());
        System.out.println(">>>>>>消费成功<<<<<<<" + result);

        //手动签收设置
        Long deliveryTag = (Long) hearders.get(AmqpHeaders.DELIVERY_TAG);

        //手动签收消息
        channel.basicAck(deliveryTag, false);
    }
}
