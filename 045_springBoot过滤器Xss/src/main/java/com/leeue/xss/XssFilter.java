package com.leeue.xss;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 这个是一个XSS 过滤器  拦截所有请求
 *
 * @author liyue
 * @date 2019-08-06 16:15
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //程序防止xss攻击的原理
        //1.使用过滤器拦截所有的参数
        HttpServletRequest req = (HttpServletRequest) request;
        //2.重写 getParameter方法 //3.使用(StringEscapeUtils.escapeHtml(name)) 转换特殊参数(
        XssHttpServletRequestWrapper requestWrapper = new XssHttpServletRequestWrapper(req);

        //放行
        chain.doFilter(requestWrapper, response);

    }

    @Override
    public void destroy() {

    }
}
