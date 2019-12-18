package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot整合 Security
 * <p>
 * admin 账号:开放的所有的权限
 * test  账号:只开放增加接口
 *
 * @author liyue
 * @date 2019/12/18 14:27
 */
@SpringBootApplication
public class AppSecurity {

    public static void main(String[] args) {
        SpringApplication.run(AppSecurity.class, args);
    }
}
