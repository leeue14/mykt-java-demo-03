package com.leeue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author liyue
 * @date 2019-08-16 14:50
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.com.leeue.leeue.mapper"})
@EnableCaching //开启缓存
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}
