package com.leeue.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 发布订阅模式 配置的交换机类型为 fanout
 * </p>
 *
 * @author liyue
 * @date 2019/12/11 19:02
 */
@Configuration
@Component
public class FanoutConfig {

    /******死信队列应用*******/

    private static final String deadQueueName = "dead_queue";
    private static final String deadRoutingKey = "dead_roting_key";
    private static final String deadExchangeName = "dead_exchange";

    /**
     * 死信队列 交换机标识符
     */
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    /**
     * 1.定义队列 邮件队列名称
     */
    private static final String FANOUT_EMAIL_QUEUE = "fanout_email_queue";
    /**
     * 1.定义队列 短信队列名称
     */
    private static final String FANOUT_SMS_QUEUE = "fanout_sms_queue";

    /**
     * 定义交换机
     */
    private static final String FANOUT_EXCHANGE = "fanout_exchange";

    /**
     * 定义邮件队列
     *
     * @return
     */
    @Bean
    public Queue fanoutEmailQueue() {

        //邮件队列绑定死信队列
        Map<String, Object> args = new HashMap<>();

        args.put(DEAD_LETTER_QUEUE_KEY, deadExchangeName);
        args.put(DEAD_LETTER_ROUTING_KEY, deadRoutingKey);

        return new Queue(FANOUT_EMAIL_QUEUE, true, false, false, args);
    }

    /**
     * 定义短信队列
     *
     * @return
     */
    @Bean
    public Queue fanoutSmsQueue() {

        return new Queue(FANOUT_SMS_QUEUE);
    }

    /**
     * 定义交换机  如果使用 topic 这里就是使用topic
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {

        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**
     * 队列和交换机进行绑定 邮件和交换机绑定
     *
     * @return
     */
    @Bean
    Binding bindingExchangeEmail(Queue fanoutEmailQueue, FanoutExchange fanoutExchange) {

        return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }

    /**
     * 队列和交换机进行绑定 消息和交换机绑定  这里参数的名称 一定要和 方法名称要一致。
     * 注入的原理是这样
     * <bean id = "fanoutExchange" class="Queue" ></bean> 所以根据参数名称来找bean
     *
     * @return
     */
    @Bean
    Binding bindingExchangeSms(Queue fanoutSmsQueue, FanoutExchange fanoutExchange) {

        return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
    }

    /***************************************RabbitMQ死信队列***************************************/

    /**
     * 1. 创建死信队列
     */
    @Bean
    public Queue deadQueue() {

        Queue queue = new Queue(deadQueueName, true);

        return queue;
    }

    /**
     * 2.创建死信交换机
     *
     * @return
     */
    @Bean
    public DirectExchange deadExchange() {

        return new DirectExchange(deadExchangeName);
    }

    /**
     * 3、 绑定死信队列和死信交换机
     *
     * @param deadQueue
     * @param deadExchange
     * @return
     */
    @Bean
    public Binding bindingDeadExchange(Queue deadQueue, DirectExchange deadExchange) {

        return BindingBuilder.bind(deadQueue).to(deadExchange).with(deadRoutingKey);
    }
}
