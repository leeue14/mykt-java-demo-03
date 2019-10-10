package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liyue
 * @date 2019/10/10 18:44
 */
@SpringBootApplication
@EnableEurekaClient
public class SRApp {

    public static void main(String[] args) {
        SpringApplication.run(SRApp.class,args);
    }
}
