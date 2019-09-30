package com.leeue.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 生成令牌
 *
 * @author liyue
 * @date 2019-08-07 15:58
 */
public class TokenUtils {

    private static Map<String, Object> tokenMaps = new HashMap<>();

    //什么是token?  token是令牌，表示是一个临时使用但是不允许重复的值 且唯一

    //使用token防止令牌重复提交

    //代码步骤：

    /**
     * 1.获取令牌
     * 2.判断令牌是否在缓存中有对应的数据
     * 3.如何缓存没有该令牌的话,就直接报错(返回请勿重复提交)
     * 4.如果缓存中有该令牌，就可以执行该业务逻辑
     * 5.执行完业务逻辑之后，直接删除该令牌
     */

    public static synchronized String getToken() {
        //在分布式下使用token都是全局 业务逻辑 ID 来做的  加上锁，要考虑到多线程的情况下来获取是唯一的 分布式这种是不靠谱的
        String token = "token" + UUID.randomUUID();

        //这里的 值 在正式项目中要用 用户id 或者其他
        tokenMaps.put(token, token);

        return token;
    }

    public static Boolean getToken(String token) {
        String tokenMap = (String) tokenMaps.get(token);
        if (StringUtils.isEmpty(tokenMap)) {
            return false;
        }
        //token 获取成功后 要删除 token  保证幂等就是要 保证这个token只能用一次
        removeToken(token);
        return true;
    }

    public static void removeToken(String token) {
        tokenMaps.remove(token);
    }
}
