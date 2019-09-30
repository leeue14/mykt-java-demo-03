package com.leeue.common.security.aop;

import com.leeue.common.exception.ExceptionEnum;
import com.leeue.common.exception.ExceptionFactory;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author liyue
 * @date 2019/9/19 11:36
 */
@Aspect
@Component
public class TestAuthorityInterceptor {

    private static final String FILED_SIGN = "sign";

    private static final String filed_sign = "nonce";

    private static final String FIELD_TIMESTAMP = "timestamp";

    private static final String FIELD_ACCESS_ID = "accessId";

    @Resource
    private HttpServletRequest request;


    @Around("@annotation(authority)")
    public Object around(ProceedingJoinPoint joinPoint, TestAuthority authority) throws Throwable {
        String accessId = request.getHeader("accessId");
        String nonce = request.getHeader("nonce");
        String action = request.getHeader("action");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");


        // 验证公共参数完整性
        if (StringUtils.isEmpty(accessId)) {
            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "accessId必传");
        }
        if (StringUtils.isEmpty(nonce)) {
            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "nonce必传");
        }
        if (StringUtils.isEmpty(action)) {
            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "action必传");
        }
        if (StringUtils.isEmpty(timestamp)) {
            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "timestamp必传");
        }
        if (StringUtils.isEmpty(sign)) {
            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "sign必传");
        }
        //验证

        // 时间戳验证
        /*if (!SecurityUtils.checkTimestamp(timestamp)) {
            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "timestamp错误");
        }*/
        // accessId验证
      /*  if (apiCredentials == null) {
            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "accessId错误");
        }*/

        // 签名认证
       /* if (!SecurityUtils.checkSign(requestMap, apiCredentials.getAccessKey(), sign)) {

            throw ExceptionFactory.systemException(ExceptionEnum.PARAM_ERROR, "sign错误");
        }*/

        Object object = joinPoint.proceed();
        return object;
    }
}
