package com.leeue.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断是否认证成功或失败
 *
 * @author liyue
 * @date 2019/12/18 15:39
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authentication) throws IOException, ServletException {

        System.out.println("登录失败");

        httpServletResponse.sendRedirect("http://www.baidu.com");
    }
}
