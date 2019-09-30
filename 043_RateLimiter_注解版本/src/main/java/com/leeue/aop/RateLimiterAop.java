package com.leeue.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.leeue.annotation.ExtRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * AOP拦截 对接口进行限流
 * <p>
 * 使用AOP环绕通知 判断拦截所有的 springMVC请求 判断请求方法上是否存在ExRateLimiter注解
 * <p>
 * AOP 创建方式有两种 一种是注解版本 一种是xml版本
 *
 * @author liyue
 * @date 2019-08-02 18:10
 */
@Aspect  //首先要定义切面
@Component
public class RateLimiterAop {


    //一个请求地址，只有一个 RateLimiter 才能做到限流
    private Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap();

    //1.定义切入点  拦截 com.com.leeue.leeue.controller下面的所有类 所有方法 任意参数
    @Pointcut("execution(public * com.leeue.controller.*.*(..))")
    public void rlAop() {

    }

    //2.使用 AOP 环绕通知判断拦截所有SpringMvc请求 判断方法上是否存在ExRateLimiter注解

    @Around("rlAop()")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1.判断请求方法上是否存在@ExRateLimiter注解
        Method signatureMethod = getSignatureMethod(proceedingJoinPoint);
        if (signatureMethod == null) {
            //直接报错
            return null;
        }
        //2.如果存在 就使用Java的反射机制获取拦截方法上自定义注解的参数
        ExtRateLimiter extRateLimiter = signatureMethod.getDeclaredAnnotation(ExtRateLimiter.class);
        if (extRateLimiter == null) {
            //直接进入实际方法请求中
            return proceedingJoinPoint.proceed();
        }
        //获取到注解上的两个参数
        double permitsPerSecond = extRateLimiter.permitsPerSecond();
        long timeout = extRateLimiter.timeout();
        //3.调用原生RateLimiter API创建令牌
        //为了保证 每个请求对应的都是单例的 RateLimiter 如 /index  --- 对应的是 RateLimiter  /order ---- 对应的是另一个RateLimiter
        //所以要使用 HashMap key为请求的url地址 value 为 RateLimiter对象
        RateLimiter rateLimiter = null;
        //获取请求URI
        String uri = this.getRequestURI();
        if (rateLimiterMap.containsKey(uri)) {
            //如果在hashMap url能坚持到RateLimiter
            rateLimiter = rateLimiterMap.get(uri);
        } else {
            //如果没有检测到就添加新的
            rateLimiter = RateLimiter.create(permitsPerSecond);
            rateLimiterMap.put(uri, rateLimiter);
        }

        //4.获取令牌桶中的令牌，如果没有在有效期获取到令牌的话，直接调用本地服务降级方法，不会进入实际请求方法中去。
        boolean tryAcquire = rateLimiter.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!tryAcquire) {
            //如果没有获取到令牌走服务的降级
            this.fallback();
        }
        //5.获取令牌桶中的令牌，如果能在有效期获取到令牌，就直接进去到实际请求的方法中去。

        return proceedingJoinPoint.proceed();

    }

    /**
     * 服务降级方法
     */
    private void fallback() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("服务繁忙,请稍后重试");
            System.out.println("走服务降级了--------");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        // 在AOP 编程中获取响应
    }

    private String getRequestURI() {
        return getRequest().getRequestURI();
    }

    private HttpServletRequest getRequest() {
        //获取到请求的url
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    /**
     * 获取到 AOP 拦截的方法
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Method getSignatureMethod(ProceedingJoinPoint proceedingJoinPoint) {
        //固定写法
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取AOP当前拦截的方法
        Method method = methodSignature.getMethod();
        return method;
    }
}
