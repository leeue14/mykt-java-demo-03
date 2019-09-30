package com.leeue.xss;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 重写Request方法
 *
 * @author liyue
 * @date 2019-08-06 16:38
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    /**
     * 要重写 getParameter方法
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String olValue = super.getParameter(name);
        if (!StringUtils.isEmpty(olValue)) {
            System.out.println("原来参数:" + olValue + "      " + "转义后参数" + StringEscapeUtils.escapeHtml4(name));
            //将参数进行转义
            olValue = StringEscapeUtils.escapeHtml4(name);


        }
        return olValue;
    }
}
