package com.leeue;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * springcloud stream 消费者
 *
 * @author liyue
 * @date 2019/12/18 12:00
 */
@Component
public class Consumer {

    @StreamListener("my_stream_test")
    public void listener(String msg) {

        System.out.println("消费者获取生产者消息:" + msg);
    }
}
