package com.leeue.common.exception;

import com.leeue.common.resp.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * SpringBoot 全局异常捕获
 * </p>
 *
 * @author liyue
 * @date 2019/9/19 11:56
 */
@Slf4j
@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    public Resp handle(SystemException systemException) {


        return Resp.error(systemException.getMsg(), systemException.getCode());
    }
}
