package com.leeue.sigle;

/**
 * 单例模式创建方式三： 线程安全加锁的 懒汉模式
 *
 * @author liyue
 * @date 2020/2/12 17:14
 */
public class User03 {

    private static User03 user03;

    private User03() {

    }

    // 加了 synchronized 锁 就可以保证只有一个线程进行访问。
    public static synchronized User03 getUser03() {

        if (user03 == null) {

            user03 = new User03();

            return user03;
        }

        return user03;
    }
}
