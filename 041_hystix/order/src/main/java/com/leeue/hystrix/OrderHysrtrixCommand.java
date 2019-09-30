package com.leeue.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.leeue.service.MemberService;
import com.netflix.hystrix.*;

import javax.annotation.Resource;

/**
 * 使用服务隔离 线程池隔离来实现服务的降级和熔断 继承HystrixCommand就行了
 * @author liyue
 * @date 2019-05-23 16:51
 */
public class OrderHysrtrixCommand extends HystrixCommand<JSONObject> {
    //这里返回JSONObject是因为接口调用的返回类型是Object

    @Resource
    MemberService memberService;

    public OrderHysrtrixCommand(MemberService memberService) {
        super(setter());
        this.memberService = memberService;

    }



    @Override
    protected JSONObject run() throws Exception {
        JSONObject member = memberService.getMember();
        System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
        return member;
    }

    @Override
    protected JSONObject getFallback() {
        // 如果Hystrix发生熔断，当前服务不可用,直接执行Fallback方法
        System.out.println("系统错误！");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 500);
        jsonObject.put("msg", "系统错误！");
        return jsonObject;
    }


    private static Setter setter() {

        // 服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("members");
        // 服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("member");
        // 线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("member-pool");
        // #####################################################
        // 线程池配置 线程池大小为10,线程存活时间15秒 队列等待的阈值为100,超过100执行拒绝策略
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(10)
                .withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);
        // ########################################################
        // 命令属性配置Hystrix 开启超时
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                // 采用线程池方式实现服务隔离
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                // 禁止超时  Hystrix是默认禁止超时的，如果不设置为false,远程调用超时了就会走getFallBack
                .withExecutionTimeoutEnabled(false).withExecutionTimeoutInMilliseconds(30000);

        return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

    }

}
