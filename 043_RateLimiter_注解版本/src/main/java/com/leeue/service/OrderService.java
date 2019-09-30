package com.leeue.service;

import org.springframework.stereotype.Service;

/**
 * @author liyue
 * @date 2019-07-18 17:22
 */
@Service
public class OrderService {

    public Boolean addOrder() {

        System.out.println("添加订单成功----------调用了数据库添加订单成功");

        return true;
    }
}
