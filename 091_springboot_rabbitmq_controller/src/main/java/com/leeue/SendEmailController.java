package com.leeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyue
 * @date 2019/12/13 11:00
 */
@RestController
@SpringBootApplication
public class SendEmailController {

    @GetMapping("sendEmail")
    public Map<String, Object> sendEmail(String email) {

        System.out.println("开始发送邮件:" + email);

        Map<String, Object> result = new HashMap<>();

        result.put("code", 200);
        result.put("msg", "邮件发送成功");

        System.out.println("发送邮件成功");

        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(SendEmailController.class, args);
    }
}
