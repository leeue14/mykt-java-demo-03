package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 搭建SpringCloud Config环境
 *
 *  在git 环境上创建配置文件名称需要规范化:  服务名称-环境.properties
 * @author liyue
 * @date 2019/10/15 17:58
 */
@SpringBootApplication
@EnableConfigServer
public class AppConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigServer.class,args);
    }
}
