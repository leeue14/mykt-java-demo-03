package com.leeue;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liyue
 * @date 2019/9/26 15:16
 */
public class LockService {
    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(200);
        //设置最大空闲数
        config.setMaxIdle(8);
        //设置最大等待时间
        config.setMaxWaitMillis(1000 * 1000);
        //在 brrow一个jedis实例时，是否需要验证，若为true,则所有的jedis实例均是可用的。
        config.setTestOnBorrow(true);

        pool = new JedisPool(config, "120.78.185.72", 6379, 3000, "123456");
    }

    private LockRedis lockRedis = new LockRedis(pool);

    // 演示 redis实现分布式锁
    public void seckill() {
        String identifierValue = lockRedis.getRedisLock(5000L, 5000L);
        if (identifierValue == null) {
            System.out.println(Thread.currentThread().getName() + ",获取锁失败，原因因为获取锁时间超时");
            return;
        }
        System.out.println(Thread.currentThread().getName() + ",获取锁成功,锁的identifierValue=" + identifierValue + "开始执行业务逻辑");
        //释放锁。
        lockRedis.unRedisLock(identifierValue);
    }
}
