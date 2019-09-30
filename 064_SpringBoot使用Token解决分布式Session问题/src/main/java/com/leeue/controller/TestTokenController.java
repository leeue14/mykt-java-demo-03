package com.leeue.controller;

import com.leeue.base.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/9/4 12:19
 */
@RestController
public class TestTokenController {


    @Autowired
    private TokenService tokenService;

    // 1. 使用token方式替代Session功能 session 原理在服务器端创建session 返回对应的sessionid 给客户端
    @RequestMapping("/put")
    public String put(String object) {
        return tokenService.put(object);
    }

    @RequestMapping("/get")
    public String get(String token) {
        return tokenService.get(token);
    }
    // 生成好的token如何存放？ 本地 移动端 存放本地文件 浏览器 存放在cookie
    // http 请求如何传递呢？ 请求头 最好建议存放在请求

}
