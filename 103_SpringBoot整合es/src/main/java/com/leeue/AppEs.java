package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author liyue
 * @date 2019/10/29 14:27
 */
@SpringBootApplication
@EnableElasticsearchRepositories
public class AppEs {
    public static void main(String[] args) {
        SpringApplication.run(AppEs.class,args);
    }
}
