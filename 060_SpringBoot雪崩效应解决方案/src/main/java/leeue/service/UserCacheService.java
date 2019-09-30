package leeue.service;

import leeue.entity.Users;
import leeue.mapper.UserMapper;
import leeue.utils.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 单体应用雪崩效应解决方案  使用单体锁来解决
 * 演示 Redis 击穿问题解决方案
 *
 * @author liyue
 * @date 2019/8/26 14:13
 */
@Service
public class UserCacheService {

    private UserMapper userMapper;
    private RedisService redisService;


    //定义一个单体锁
    private Lock lock = new ReentrantLock();

    public String getUser(Long id) {
        //1.先查redis
        // 1.先查询redis
        String key = this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName()
                + "-id:" + id;
        String userName = redisService.getString(key);
        if (!StringUtils.isEmpty(userName)) {
            return userName;
        }
        String resultUserName = null;
        try {
            //开启锁  多线程情况下，这段代码只有一个线程才会去执行。如果是集群的服务器，这里就会使用分布式锁来解决。实际上只有一个请求在查询数据库
            //这里也可以写成限流
            lock.lock();
            //2.没有查询到就查询数据库
            Users user = userMapper.getUser(id);
            if (user == null) {
                return null;
            }
            resultUserName = user.getName();
            redisService.setSet(key, resultUserName);
        } catch (Exception e) {

        } finally {
            //释放锁
            lock.unlock();
        }
        //3.直接返回
        return resultUserName;
    }


    /**
     * 解决Redis击穿问题
     *
     * @param id
     * @return
     */
    public String getUser2(Long id) {
        //1.先查redis
        // 1.先查询redis
        String key = this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName()
                + "-id:" + id;
        String userName = redisService.getString(key);
        if (!StringUtils.isEmpty(userName)) {
            return userName;
        }
        String resultUserName = null;

        //如果数据库没有对应的数据信息的时候
        Users user = userMapper.getUser(id);
        if (user == null) {
            //如果当前数据库没有对应的数据信息的时候，就将当前的redis值设置为空
            resultUserName = "${null}";
        } else {
            //如果当前数据库有对应的数据，获取到该数据名称
            resultUserName = user.getName();
        }
        redisService.setSet(key, resultUserName);
        //3.直接返回
        return resultUserName;
    }

}
