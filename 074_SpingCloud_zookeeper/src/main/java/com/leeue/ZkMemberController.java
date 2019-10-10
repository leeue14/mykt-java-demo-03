package com.leeue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019/10/10 14:42
 */
@RestController
@RequestMapping("/member")
public class ZkMemberController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getMember")
    public String getMember() {
        return "会员服务，订单服务调用接口为" + serverPort;
    }
}
