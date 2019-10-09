package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liyue
 * @date 2019/9/29 17:10
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApp2 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApp2.class, args);
    }
}
