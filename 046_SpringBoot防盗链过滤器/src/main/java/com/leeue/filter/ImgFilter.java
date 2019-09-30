package com.leeue.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 这个是一个 防盗链的过滤器
 *
 * @author liyue
 * @date 2019-08-07 11:51
 */
@WebFilter(filterName = "imgfilter", urlPatterns = "/*")
public class ImgFilter implements Filter {

    //不用拦截的域名
    @Value("${domain.name}")
    private String domainName;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //1.获取请求头中的来源字段 Referer
        HttpServletRequest req = (HttpServletRequest) request;
        String referer = req.getHeader("Referer");
        if (StringUtils.isEmpty(referer)) {
            request.getRequestDispatcher("/imgs/error.png").forward(request, response);
            return;
        }
        //2.判断请求头中的域名是否和限制的域名一致
        String domain = this.getDomain(referer);
        if (!domain.equals(domainName)) {
            request.getRequestDispatcher("/imgs/error.png").forward(request, response);
            return;
        }

        //如果一致直接放行
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

    /**
     * 获取url对应的域名
     *
     * @param url
     * @return
     */
    public String getDomain(String url) {
        String result = "";
        int j = 0, startIndex = 0, endIndex = 0;
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '/') {
                j++;
                if (j == 2)
                    startIndex = i;
                else if (j == 3)
                    endIndex = i;
            }

        }
        result = url.substring(startIndex + 1, endIndex);
        return result;
    }

}
