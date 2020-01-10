package com.leeue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liyue
 * @date 2020/1/10 15:59
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.leeue.mapper"})
public class MycatApp {

    public static void main(String[] args) {

        SpringApplication.run(MycatApp.class, args);
    }
}
