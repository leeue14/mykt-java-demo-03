package com.leeue;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/12/16 17:07
 */
@RestController
@RefreshScope
public class ConfigController {


    @Value("${userAge}")
    private String userAge;


    @GetMapping("/getUserAge")
    public String getUserAge() {
        return userAge;
    }

}