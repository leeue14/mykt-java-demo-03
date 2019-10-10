package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liyue
 * @date 2019/9/29 17:45
 */
@SpringBootApplication
// 这个注解的作用是，如果注册中心服务使用的connsul 、zookeeper使用这个注解向注册中心上注册服务
@EnableDiscoveryClient
public class ZkOrderApp {

    public static void main(String[] args) {
        SpringApplication.run(ZkOrderApp.class, args);
    }
}
