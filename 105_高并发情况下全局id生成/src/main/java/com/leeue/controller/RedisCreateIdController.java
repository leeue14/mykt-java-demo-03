package com.leeue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 基于redis生成订单号  redis 天生是安全的 天生保证原子性
 * <p>
 * 如果redis 集群也会产生 数据库一样的问题，也是要设置步长
 * <p>
 *
 * </p>
 *
 * @author liyue
 * @date 2020/1/9 18:06
 */
@RestController
public class RedisCreateIdController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/create")
    public String create(String key, Long timeOut) {


        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());

        //设置起始值 设置步长  一般通过命名设置每台 redis 的步长 来生成 id 不一样
        redisAtomicLong.set(10);

        //设置步长 设置步长+10  默认是 从 0 开始的 所以如果要+10 就要设置成 9步
        redisAtomicLong.addAndGet(9);

        for (int i = 0; i < 100; i++) {

            Long incrementAndGet = redisAtomicLong.incrementAndGet();

            // %1$05d : 00037 : 表示 5位数 从第1位开始 如果不够就自动补 0
            String orderId = prefix() + String.format("%1$05d", incrementAndGet);

            System.out.println(orderId);
        }

        //键 要设置过期时间 因为怕用完了。最好设置 24小时过期 因为时间已经改变  redis 会占宽带性能


        return "ok";
    }

    public static String prefix() {

        Date dt = new Date();

        // 在正式环境的时候 设置这个一般是从毫秒开始
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        String temp_str = simpleDateFormat.format(dt);

        System.out.println(temp_str);

        return temp_str;
    }
}
