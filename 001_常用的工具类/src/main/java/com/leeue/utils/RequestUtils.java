package com.leeue.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestUtils 对于请求方法的一些常用工具类
 *
 * @author liyue
 * @date 2019-08-13 10:23
 */
public class RequestUtils {


    /**
     * 获取到 当前请求 路径 url
     *
     * @return
     */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }

    /**
     * 获取到 当前请求的request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        //获取到请求的url
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}
