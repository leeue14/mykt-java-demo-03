package com.leeue.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.leeue.annotation.ExtRateLimiter;
import com.leeue.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 这个是使用 RateLimiter框架来实现 令牌桶限流 算法
 *
 * @author liyue
 * @date 2019-07-18 17:20
 */
@RestController
public class IndexOrderController {

    @Autowired
    private OrderService orderService;

    // create方法 中传入一个参数 以每秒为单位固定的速率值 1r/s 每秒往同种存入令牌
    //这里令牌生成的速率也就是 每秒 放入 1个 令牌进入桶中
    RateLimiter rateLimiter = RateLimiter.create(1); //注意这个是单线程 放在这里

    //这里相当于该接口每秒时间只支持一个客户端访问
    @GetMapping("/addOrder")
    public String addOrder() {

        //注意:限流处理一般都是在 网关 做限流处理的。这里是为了演示RateLimiter 框架来实现令牌桶限流的

        //1. 首先做限流
        double acquire = rateLimiter.acquire();
        //这个 acquire 表示用户需要等待多长时间才能获取到令牌
        //如果: 用户获取不到令牌 就会一直等待  这个时候就要
        // 设置服务降级处理(相当于配置在规定时间内 如果没有获取到令牌的话就会走服务降级处理)
        //    System.out.println("从桶中获取令牌等待的时间:" + acquire);
        //这个是设置 如果在500毫秒 内没有获取到令牌的话 就会直接走服务降级处理
        Boolean tryAcquire = rateLimiter.tryAcquire(500, TimeUnit.MICROSECONDS);
        //如果为true 就说明拿到令牌 如果为false 没有拿到令牌
        if (!tryAcquire) {
            System.out.println("走服务降级处理了");
            return "别抢了,再抢也是一直在等待的。";
        }
        //2.业务逻辑处理
        Boolean addOrderResult = orderService.addOrder();
        if (addOrderResult) {
            return "恭喜您!抢购商品成功";
        }
        return "抢购商品失败了";
    }


    /**
     * RateLimiter 是对接口做限流处理
     *
     * @return
     * @ExtRateLimiter(permitsPerSecond = 1.0, timeout = 500)
     * 这个意思是 每秒产生一个令牌，超时时间是 500毫秒 如果500毫秒没有获取到令牌就会走服务降级处理
     */

    //以每秒 1 个令牌的速度 放入令牌桶中 超时时间是500毫秒
    @GetMapping("/save")
    @ExtRateLimiter(permitsPerSecond = 1.0, timeout = 500)
    public void saveOrder() {
        System.out.println("findIndx 成功保存" + System.currentTimeMillis());
        //  return
    }
}
