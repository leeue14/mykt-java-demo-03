package com.leeue.controller;

import com.leeue.common.resp.Resp;
import com.leeue.common.security.aop.TestAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/9/19 11:31
 */
@RestController
public class TestController {

    @PostMapping("/test")
    @TestAuthority
    public Resp<String> testController() {

        return Resp.ok("我是测试返回");
    }
}
