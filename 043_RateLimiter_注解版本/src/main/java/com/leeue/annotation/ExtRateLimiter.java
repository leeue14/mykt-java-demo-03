package com.leeue.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义注解 版本 RateLimiter框架
 * <p>
 * 自定义服务限流注解框架
 *
 * @author liyue
 * @date 2019-08-02 16:57
 */
@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Documented
public @interface ExtRateLimiter {

    //以每秒为单位固定的速率值往令牌桶中添加令牌
    double permitsPerSecond();

    //超时时间 在规定的毫秒数中，如果没有获取到令牌的话，就直接走服务降级处理
    long timeout();

}
