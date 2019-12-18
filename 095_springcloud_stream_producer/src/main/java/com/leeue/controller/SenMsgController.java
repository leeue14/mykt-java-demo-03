package com.leeue.controller;

import com.leeue.stream.SendMessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 消息驱动发送消息
 *
 * @author liyue
 * @date 2019/12/18 11:41
 */
@RestController
public class SenMsgController {

    @Autowired
    private SendMessageInterface sendMessageInterface;

    /**
     * 创建生产者流程:
     * 1、创建发送消息通道
     * 2、生产者投递消息
     * <p>
     * 生产者往该通道中发送消息
     */
    @RequestMapping("/sendMsg")
    public String sendMsg() {

        String msg = UUID.randomUUID().toString();

        System.out.println("生产者发送内容msg:" + msg);

        Message build = MessageBuilder.withPayload(msg.getBytes()).build();

        sendMessageInterface.sendMsg().send(build);

        return "success";
    }
}
