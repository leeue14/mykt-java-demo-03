package com.leeue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * <p>
 * 基于redis实现分布式锁
 * </p>
 *
 * @author liyue
 * @date 2019/9/26 11:08
 */
public class LockRedis {
    //基于redis实现分布所锁代码思路，核心方法 获取锁，释放锁

    //redis线程池
    private JedisPool jedisPool;

    //多个客户端 创建相同的key
    private String redisLockKey = "redis_lock";

    public LockRedis(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    // redis 是以key 和 value 来存储的，这里的value 我们可以使用随机不能够重复的数组，作为锁的id。

    // redis 实现分布式锁，有两个超时时间:
    /**
     * 超时时间
     1. 在获取锁之前的超时时间，在尝试获取锁的时候，如果在规定的时间内还没有获取锁，直接放弃。
     2. 在获取到锁之后的超时时间，在获取锁成功之后，对应的key,应该有个有效期，也就是锁应该在对应的时间里失效。
     */
    /**
     * @param acquireTimeout 在获取锁之前的超时时间  获取锁超时时间
     * @param timeOut        获取锁之后，锁的失效时间
     */
    //获取锁
    public String getRedisLock(Long acquireTimeout, Long timeOut) {
        Jedis conn = null;
        try {
            //1.建立redis连接
            conn = jedisPool.getResource();
            //2.定义 redis 对应的 key的value的值。(uuid) 作用就是释放锁的时候使用。
            String identifierValue = UUID.randomUUID().toString();
            //3.在获取锁之的超时间时间 锁的失效时间。
            int expireLockTime = (int) (timeOut / 1000); //因为 Redis 单位是秒，传进来的都是毫秒
            //4.定义在获取锁之前的超时时间
            //5.使用循环机制保证重复进行尝试获取锁(类似乐观锁) 要在规定的 acquireTimeOut 时间保证重复进行尝试获取锁
            Long endTime = System.currentTimeMillis() + acquireTimeout;
            //分布式锁
            while (System.currentTimeMillis() < endTime) {
                //6.是用setnx 命令插入对应的 redislockkey ,如果返回1 成功获取到锁
                if (conn.setnx(redisLockKey, identifierValue) == 1) {
                    //说明插入成功，成功获取到锁 设置value 失效时间 设置key的有效期
                    conn.expire(redisLockKey, expireLockTime);
                    return identifierValue;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        //如果在规定的时间里没有获取到锁，就返回null
        return null;
    }


    //释放锁 有两种方式，1.是 过期自动释放，2.业务执行完成了，就释放了。
    public void unRedisLock(String identifierValue) {
        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            if (!conn.get(redisLockKey).equals(identifierValue)) {  //这一点要记住
                // 在分布式情况下防止两个线程 在分布式情况下线程a 创建了 key 后，线程b删除了key
                System.out.println(conn.get(redisLockKey) + "  现在要删除的value=" + identifierValue);
            }
            if (conn.get(redisLockKey).equals(identifierValue)) {
                //释放锁，必须锁，自己删除 自己创建的锁。
                conn.del(redisLockKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
