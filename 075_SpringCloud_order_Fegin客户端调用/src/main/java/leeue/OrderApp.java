package leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liyue
 * @date 2019/9/29 17:53
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients //使用Feign 这个扫描要加上 可以开启 Feign客户端调用权限
public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}
