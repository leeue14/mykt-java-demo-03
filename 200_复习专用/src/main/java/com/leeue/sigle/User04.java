package com.leeue.sigle;

/**
 * 单例模式创建方式四： 双重校验锁饿汉模式
 *
 * @author liyue
 * @date 2020/2/12 17:17
 */
public class User04 {

    //加上volatile 关键字，user就对线程有了可见性
    private static volatile User04 user04;

    private User04() {

    }

    public static User04 getUser04() {

        if (user04 == null) {

            //使用 synchronized 包裹代码块。双重判断。static 修饰的锁是字节码文件
            synchronized (User04.class) {

                if (user04 == null) {

                    user04 = new User04();

                    return user04;
                }
            }
        }
        return user04;
    }
}
