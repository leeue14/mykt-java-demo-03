package com.leeue.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liyue
 * @date 2019/9/29 18:11
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * SpringCloud 中有两种调用方式 一种是Rest 一种是fegin
     *
     * @return
     */
    @Autowired
    private RestTemplate restTemplate; //底层就是使用的httpclient

    @GetMapping("/getOrder")
    public String getOrder() {
        //有两种方式进行调用，一种是通过服务名进行调用，一种是通过地址直接进行调用。
        //第一种方式
        //   String result = restTemplate.getForObject("http://127.0.0.1:8001/member/getMember", String.class);
        //第二种方式 想要rest方式以别名的方式进行依赖调用，依赖ribbon负载均衡器 需要加上一个注解 @LoadBalanced
        String url = "http://leeue-member/member/getMember";
        String result = restTemplate.getForObject(url, String.class);
        return "我是order调用member服务后的结果=" + result;
    }

    //解决 Consider defining a bean of type 'org.springframework.web.client.RestTemplate' in your configuration.

    /**
     * Description:
     * <p>
     * A component required a bean of type 'org.springframework.web.client.RestTemplate' that could not be found.
     */

    //应该要把RestTemplate 模板注入到容器中去

    @Bean
    @LoadBalanced  //这个注解的作用就是 让 RestTemplate 在请求时，拥有客户端负载均衡的能力,这样如果会员服务集群了，就会进行轮训调用
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
