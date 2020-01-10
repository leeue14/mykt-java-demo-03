package com.leeue.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 基于redis生成id 方式
 * </p>
 *
 * @author liyue
 * @date 2020/1/9 17:53
 */
@Component
public class RedisCreateId {

    @Autowired
    private RedisTemplate redisTemplate;

    public String order(String key, Long timeOut) {

        //每个key 都是从 1开始的，
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());

        redisAtomicLong.set(1);

        //设置redis 步长增长为2
        redisAtomicLong.addAndGet(1);

        long andIncrement = redisAtomicLong.getAndIncrement();

        //00002
        String orderId = prefix() + String.format("%1$05d", andIncrement);

        System.out.println("生成的订单号为" + orderId);

        if (redisAtomicLong != null && redisAtomicLong.longValue() > 0 && timeOut > 0) {

            //初始设置过期时间
            redisAtomicLong.expire(timeOut, TimeUnit.SECONDS);
        }
        return "success";
    }

    public static String prefix() {

        Date dt = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        String temp_str = simpleDateFormat.format(dt);

        System.out.println(temp_str);

        return temp_str;
    }
}
