package com.leeue.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liyue
 * @date 2019/12/12 11:16
 */
@Component
@RabbitListener(queues = "fanout_sms_queue")
public class SmsConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("短信消费者获取生产者的消息:" + msg);
    }
}
