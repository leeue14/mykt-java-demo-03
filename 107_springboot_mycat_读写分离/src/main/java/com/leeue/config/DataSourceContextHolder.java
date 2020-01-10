package com.leeue.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false) // 禁止懒加载
public class DataSourceContextHolder {

    /**
     * 采用ThreadLocal 保存本地多数据源
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     *
     * @param dbType
     */
    public static void setDbType(String dbType) {

        contextHolder.set(dbType);
    }

    public static String getDbType() {

        return contextHolder.get();
    }

    public static void clearDbType() {

        contextHolder.remove();
    }

}
