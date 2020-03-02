package com.leeue.sigle;

/**
 * 单例模式创建方式一： 饿汉模式创建
 *
 * @author liyue
 * @date 2020/2/12 15:44
 */
public class User01 {

    //1.一开始就创建对象
    public static User01 user01 = new User01();

    //2.私有化构造方法

    private User01() {

    }

    //3.提供一个对外的可以获取当前对象的方法

    public static User01 getUser01() {

        return user01;
    }

}
