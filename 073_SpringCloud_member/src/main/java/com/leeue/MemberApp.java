package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liyue
 * @date 2019/9/29 17:45
 */
@SpringBootApplication
@EnableFeignClients
public class MemberApp {

    public static void main(String[] args) {
        SpringApplication.run(MemberApp.class, args);
    }
}
