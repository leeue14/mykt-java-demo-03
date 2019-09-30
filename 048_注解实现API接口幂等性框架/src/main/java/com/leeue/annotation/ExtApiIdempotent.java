package com.leeue.annotation;

import com.leeue.utils.ConstantUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 防止接口重复提交  这个需要支持网络延迟和表单提交的幂等性
 * 只要加上这个注解，就会自动对接口验证token 支持网络延迟 和表单提交
 *
 * @author liyue
 * @date 2019-08-08 17:58
 */

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtApiIdempotent {
    String type() default ConstantUtils.EXTAPIFROM;
}
