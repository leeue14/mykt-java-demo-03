package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author liyue
 * @date 2019/12/6 15:16
 */
@SpringBootApplication
/**启用定时任务**/
@EnableScheduling
public class ActiveMqProductApp {

    public static void main(String[] args) {

        SpringApplication.run(ActiveMqProductApp.class, args);
    }
}
