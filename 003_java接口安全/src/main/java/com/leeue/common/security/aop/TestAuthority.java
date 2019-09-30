package com.leeue.common.security.aop;

import java.lang.annotation.*;

/**
 * @author liyue
 * @date 2019/9/19 11:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAuthority {
}
