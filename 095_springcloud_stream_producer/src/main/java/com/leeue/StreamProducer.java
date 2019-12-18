package com.leeue;

import com.leeue.stream.SendMessageInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * springcloud stream 使用
 * * @EnableBinding(SendMessageInterface.class):绑定通道
 *
 * @author liyue
 * @date 2019/12/18 11:48
 */
@SpringBootApplication
@EnableBinding(SendMessageInterface.class)
public class StreamProducer {

    public static void main(String[] args) {

        SpringApplication.run(StreamProducer.class, args);
    }
}
