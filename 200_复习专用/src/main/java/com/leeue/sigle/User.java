package com.leeue.sigle;

/**
 * 单例模式创建方式五： 使用枚举方式创建实现单例模式
 *
 * @author liyue
 * @date 2020/2/12 17:27
 */
public class User {

    //1.定义枚举里面复制创建对象
    private static enum UserEnum {

        GET_USER;

        private User user;

        private UserEnum() {

            user = new User();
        }

        public User getUser() {

            return user;
        }
    }

    //2.私有构造方法

    private User() {

    }

    //3.提供一个对外获取对象的方法

    private static User getUser() {

        return UserEnum.GET_USER.getUser();
    }

}
