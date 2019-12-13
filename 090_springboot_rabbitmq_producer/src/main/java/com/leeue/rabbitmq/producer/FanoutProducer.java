package com.leeue.rabbitmq.producer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 生产者
 * </p>
 *
 * @author liyue
 * @date 2019/12/11 19:28
 */
@Component
public class FanoutProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queueName) {

        System.out.println("发送队列名称:" + queueName);

        Map<String, String> msgMap = new HashMap<>();

        msgMap.put("email", "leeue@foxmail.com");
        msgMap.put("timestamp", System.currentTimeMillis() + "");

        String msg = JSONObject.toJSONString(msgMap);

        //生产者设置消息id
        Message message = MessageBuilder.withBody(msg.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID() + "设置全局消息id").build();

        //先发送消息  路由策略等，都是这样操作的
        amqpTemplate.convertAndSend(queueName, message);
    }

}
