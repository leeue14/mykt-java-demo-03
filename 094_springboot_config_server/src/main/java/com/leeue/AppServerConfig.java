package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liyue
 * @date 2019/12/16 16:48
 */
@EnableEurekaClient
/***加上这个注解表示是分布式配置中心  访问远程上配置文件地址http://127.0.0.1:8002/appConfig-dev.yml***/
@EnableConfigServer
@SpringBootApplication
public class AppServerConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppServerConfig.class, args);
    }
}