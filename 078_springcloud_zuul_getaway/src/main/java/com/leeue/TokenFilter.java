package com.leeue;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建 Zuul网关过滤器
 *  //案例:拦截所有的服务接口，判断服务接口上是否有传递userToken参数
 * @author liyue
 * @date 2019/10/16 20:15
 */
@Component
public class TokenFilter extends ZuulFilter {
    /**
     * 过滤器类型
     * "pre" : 表示在请求之前执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行的顺序，当一个请求在同一个阶段的时候存在多个过滤器时候，多个过滤器执行顺序。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行这个过滤器 为false 就不会进行执行 为true就会进行执行这个过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //1.获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //2.获取request
        HttpServletRequest request = requestContext.getRequest();
        //3.从request里面获取token值，公司里面都是从请求头中获取的
        String userToken = request.getParameter("token");
        if(StringUtils.isEmpty(userToken)){
            //这样就表示代码不会继续执行了
            requestContext.setSendZuulResponse(false);
            //返回一个错误的提示
            requestContext.setResponseBody("userToken is null");
            requestContext.setResponseStatusCode(400);
        }
        return null;
    }
}
