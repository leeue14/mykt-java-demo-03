package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author liyue
 * @date 2019-08-06 16:55
 */
@SpringBootApplication
@ServletComponentScan  //这个注解一定要加上，否则扫描不到 XssFilter 这个类
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}
