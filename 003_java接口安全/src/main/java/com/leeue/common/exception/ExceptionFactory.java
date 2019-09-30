package com.leeue.common.exception;

/**
 * @author liyue
 * @date 2019/9/19 11:42
 */
public class ExceptionFactory {

    public static SystemException systemException(ExceptionEnum exceptionEunm, String msg) {

        return new SystemException(exceptionEunm, msg);
    }
}
