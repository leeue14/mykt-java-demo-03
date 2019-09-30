package com.leeue;

import com.leeue.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author liyue
 * @date 2019-08-14 16:40
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy  //表示开启网关代理功能
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }


    //参数过滤器
    @Bean
    public TokenFilter accessFilter() {
        return new TokenFilter();
    }

}
