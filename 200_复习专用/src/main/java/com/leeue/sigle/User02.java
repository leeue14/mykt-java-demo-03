package com.leeue.sigle;

/**
 * 单例模式创建方式二： 懒汉模式
 *
 * @author liyue
 * @date 2020/2/12 15:50
 */
public class User02 {

    //1.开始只定义自己
    private static User02 user02;

    //2.构造方法私有化
    private User02() {

    }

    //3.对外提供一个可以获取到对象的方法
    public static User02 getUser02() {

        if (user02 == null) {

            user02 = new User02();

            return user02;
        }

        return user02;
    }
}
