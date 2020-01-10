package com.leeue.aop;

import com.leeue.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 根据方法 名称的前缀 来判断 选择哪个数据源 进行操作
 *
 * @author liyue
 * @date 2020/1/10 17:16
 */
@Aspect
@Component
@Lazy(false)
@Order(0)  // order 设定 AOP 的执行顺序 是在数据库的事务之前的
public class SwitchDataSourceAop {

    @Before("execution(* com.leeue.service.*.*(..))")
    public void process(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
                || methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")) {

            //切换成 读数据源
            DataSourceContextHolder.setDbType("selectDataSource");

        } else {

            //切换成 写数据源
            DataSourceContextHolder.setDbType("updateDataSource");
        }

    }
}
