package com.leeue.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 注入队列到容器里
 *
 * @author liyue
 * @date 2019/12/6 15:17
 */
@Component
public class ConfigQueue {

    @Value("${queue}")
    private String queue;

    @Value("${topic}")
    private String topic;

    /**
     * 加 bean 表示这个 Queue注入到Spring容器里
     *
     * @return
     */
    @Bean
    public Queue queue() {

        return new ActiveMQQueue(queue);
    }

    @Bean
    public Topic topic() {

        return new ActiveMQTopic(topic);
    }

}
