package com.leeue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liyue
 * @date 2019/12/18 14:27
 */
@Controller
public class OrderController {

    /**
     * 首页
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 查询订单
     */
    @RequestMapping("/showOrder")
    public String showOrder() {
        return "showOrder";
    }

    /**
     * 添加订单
     *
     * @return
     */
    @RequestMapping("/addOrder")
    public String addOrder() {
        return "addOrder";
    }

    /**
     * 修改订单
     *
     * @return
     */
    @RequestMapping("/updateOrder")
    public String updateOrder() {
        return "updateOrder";
    }

    /**
     * 删除订单
     *
     * @return
     */
    @RequestMapping("/deleteOrder")
    public String deleteOrder() {
        return "deleteOrder";
    }

    /**
     * 自定义登陆页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
