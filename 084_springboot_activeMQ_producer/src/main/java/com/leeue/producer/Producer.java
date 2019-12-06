package com.leeue.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * <p>
 * 生产者 每隔5秒发送消息到消息队列里面去
 * </p>
 *
 * @author liyue
 * @date 2019/12/6 15:15
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    /****@Scheduled(fixedDelay = 5000)****/
    public void sendMessage() {

        String msg = "开始发送消息" + System.currentTimeMillis();

        jmsMessagingTemplate.convertAndSend(queue, msg);

        System.out.println("采用点对点模式进行消息发送");
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessageByTopic() {
        String msg = "开始发送消息" + System.currentTimeMillis();

        jmsMessagingTemplate.convertAndSend(topic, msg);

        System.out.println("采用发布订阅模式进行消息发送");

    }

}
