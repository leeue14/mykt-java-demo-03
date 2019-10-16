package com.leeue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liyue
 * @date 2019/10/15 19:35
 */
@SpringBootApplication
@EnableEurekaClient
public class AppClientApp {

    @Value("${leeue}")
    private String  value;

    public static void main(String[] args) {
        SpringApplication.run(AppClientApp.class,args);
    }


}
