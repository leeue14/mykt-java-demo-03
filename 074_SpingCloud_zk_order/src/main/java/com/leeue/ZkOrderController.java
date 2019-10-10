package com.leeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liyue
 * @date 2019/10/10 14:42
 */
@RestController
@RequestMapping("/order")
public class ZkOrderController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    RestTemplate rest;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/getOrder")
    public String getMember() {
        String url = "http://zk-member/member/getMember";
        return rest.getForObject(url,String.class);
    }





}
