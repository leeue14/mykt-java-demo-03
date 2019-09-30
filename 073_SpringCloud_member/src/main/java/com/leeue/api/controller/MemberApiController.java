package com.leeue.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/9/29 17:50
 */
@RestController
@RequestMapping("/member")
public class MemberApiController {


    @Value("${server.port:8080}")
    private String port;

    @GetMapping("/getMember")
    public String getMember() {
        return "我是会员服务,端口号=" + port;
    }
}
