package com.leeue.controller;

import com.leeue.annotation.ExtApiToken;
import com.leeue.utils.ConstantUtils;
import com.leeue.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liyue
 * @date 2019-08-07 16:27
 */
@RestController
public class OrderController {


    @GetMapping("/getToken")
    public String getToken() {

        return TokenUtils.getToken();

    }


    @PostMapping("/addOrder")
    @ExtApiToken(type = ConstantUtils.EXTAPIHEAD)
    public String addOrder(@RequestParam(value = "name", required = false) String name, HttpServletRequest request) {
        //接口调用步骤: 1.获取请求头中的token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return "参数错误!";
        }
        if (!TokenUtils.getToken(token)) {
            return "请勿重复提交";
        }
        return "添加成功";
    }
}
