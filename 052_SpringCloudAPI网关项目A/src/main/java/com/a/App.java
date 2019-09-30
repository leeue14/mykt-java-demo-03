package com.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019-08-14 16:25
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class App {


    @RequestMapping("/")
    public String index() {
        return "我是A项目....";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
