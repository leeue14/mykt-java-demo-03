package com.leeue.aop;

import com.leeue.annotation.ExtApiIdempotent;
import com.leeue.annotation.ExtApiToken;
import com.leeue.utils.BaseRedisService;
import com.leeue.utils.ConstantUtils;
import com.leeue.utils.RedisToken;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liyue
 * @date 2019-08-08 18:12
 */
@Aspect
@Component
public class ExtApiIdempotentAop {

    @Autowired
    BaseRedisService baseRedisService;

    @Autowired
    RedisToken redisToken;

    //1.定义切入点
    @Pointcut("execution(public * com.leeue.controller.*.*(..))")
    public void rlAop() {

    }

    //使用前置通知 给生成token接口自动生成token
    @Before("rlAop()")
    public void doBefore(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        ExtApiToken extApiToken = methodSignature.getMethod().getDeclaredAnnotation(ExtApiToken.class);
        if (extApiToken != null) {
            //放置到前置代码上
            getRequest().setAttribute("token", redisToken.getToken());
        }
    }

    //最好使用环绕通知来实现
    @Around("rlAop()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /**
         实现步骤:
         1.拦截所有的请求
         2.判断方法上面是否存在 ExtApiToken注解
         3.如果有就进行判断
         */
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取的token
        String token = null;
        //获取到当前拦截到的方法，再获取到当前拦截到的方法上面是否有注解
        ExtApiIdempotent extApiIdempotent = methodSignature.getMethod().getDeclaredAnnotation(ExtApiIdempotent.class);
        if (extApiIdempotent == null) {
            //说明是不需要token验证的接口直接放行
            Object object = proceedingJoinPoint.proceed();
            //这个一定要返回，否则页面上都接受不到任何数据
            return object;
        }
        //获取到 这个注解的类型  type有两种，一种是请求头，一种是放在隐藏域中随着表单一起提交的 默认是从表单获取的
        String type = extApiIdempotent.type();
        HttpServletRequest request = getRequest();
        if (type.equals(ConstantUtils.EXTAPIHEAD)) {
            token = getRequest().getHeader("token");
            if (StringUtils.isEmpty(token)) {
                this.response("参数错误");
                return null;
            }

        } else {
            //获取到表单上的token
            token = getRequest().getParameter("token");
        }
        boolean isToken = redisToken.findToken(token);
        if (!isToken) {
            this.response("不允许重复提交");
            return null;
        }
        //这个方法就是放行的表示可以执行这个
        Object object = proceedingJoinPoint.proceed();
        return object;
    }


    /**
     * 工具类 获取当前的请求对象 Request
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    /**
     * 工具类 返回当前的回复信息
     *
     * @param msg
     * @throws IOException
     */
    public void response(String msg) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            writer.println(msg);
        } catch (Exception e) {

        } finally {
            writer.close();
        }

    }

}
