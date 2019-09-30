package com.leeue.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liyue
 * @date 2019/9/3 10:31
 */
@RestController
public class TestSessionController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/createSession")
    public String createSession(HttpServletRequest request, String nameValue) {
        //默认是设置成true,如果没有session 就会自动创建一个，这里设置成false表示如果没有不需要自动创建session
        HttpSession session = request.getSession();
        System.out.println(serverPort + "存入session信息  sessionId=" + session.getId() + ",nameValue=" + nameValue);
        session.setAttribute("name", nameValue);
        return "success" + serverPort;
    }


    @GetMapping("/getSession")
    public Object getSession(HttpServletRequest request) {
        //这里默认是设置为true 如果没有当前请求的session,就会自动创建一个，如果有就会获取当前的session返回 所以为了不覆盖，这里设置成false
        HttpSession session = request.getSession(false);
        Object object = null;
        if (session != null) {
            object = session.getAttribute("name");
            System.out.println(serverPort + "获取Session sessionid:信息" + session.getId());
        }
        return serverPort + object;
    }
}
