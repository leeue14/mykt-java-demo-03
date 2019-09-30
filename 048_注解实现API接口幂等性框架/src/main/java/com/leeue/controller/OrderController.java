package com.leeue.controller;

import com.leeue.annotation.ExtApiIdempotent;
import com.leeue.utils.BaseRedisService;
import com.leeue.utils.ConstantUtils;
import com.leeue.utils.RedisToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liyue
 * @date 2019-08-07 16:27
 */
@RestController
public class OrderController {

    @Autowired
    BaseRedisService baseRedisService;

    @Autowired
    RedisToken redisToken;


    @GetMapping("/getToken")
    public String getToken() {

        return redisToken.getToken();

    }


    @PostMapping("/addOrder")
    public String addOrder(@RequestParam(value = "name", required = false) String name, HttpServletRequest request) {
        //接口调用步骤: 1.获取请求头中的token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return "参数错误!";
        }
        if (!redisToken.findToken(token)) {
            return "请勿重复提交";
        }
        return "添加成功";
    }


    @PostMapping("/saveOrderApi")
    @ExtApiIdempotent(type = ConstantUtils.EXTAPIHEAD)
    public String saveOrderApi(@RequestParam(value = "name", required = false) String name, HttpServletRequest request) {
        return "添加成功";
    }
}
