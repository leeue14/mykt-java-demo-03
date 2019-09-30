package com.leeue.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 使用网关拦截参数
 *
 * @author liyue
 * @date 2019-08-14 16:48
 */
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //过滤器类型
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤器的优先级  数字越大 优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器 此处为true 说明需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //  过滤执行
        System.out.println("过滤开始执行-------------");
        return null;
    }
}
