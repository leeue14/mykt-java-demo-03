package com.leeue.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/9/25 12:33
 */
@RestController
public class Index {

    // 写这个value时候一定要写一个默认值
    @Value("${name:defaultName}")
    private String name;

    @GetMapping("test")
    public String test() {
        return name;
    }
}
