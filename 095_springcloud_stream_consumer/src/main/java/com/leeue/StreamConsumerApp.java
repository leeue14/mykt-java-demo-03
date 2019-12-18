package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 消费者消费消息
 * * @EnableBinding(ReadMessageInterface.class) 绑定消息
 *
 * @author liyue
 * @date 2019/12/18 12:05
 */
@SpringBootApplication
@EnableBinding(ReadMessageInterface.class)
public class StreamConsumerApp {

    public static void main(String[] args) {

        SpringApplication.run(StreamConsumerApp.class, args);
    }
}
