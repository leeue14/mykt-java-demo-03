package com.leeue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面跳转
 *
 * @author liyue
 * @date 2019/12/18 15:26
 */
@Controller
public class ErrorController {

    @RequestMapping("/error/403")
    public String error() {
        return "/error/403";
    }

}
