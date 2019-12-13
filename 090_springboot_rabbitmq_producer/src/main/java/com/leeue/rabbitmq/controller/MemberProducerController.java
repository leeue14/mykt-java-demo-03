package com.leeue.rabbitmq.controller;

import com.leeue.rabbitmq.producer.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/12/12 10:23
 */
@RestController
public class MemberProducerController {

    @Autowired
    private FanoutProducer fanoutProducer;

    @GetMapping("/sendMsg")
    public void sendMsg(String queueName) {

        fanoutProducer.send(queueName);
    }
}
