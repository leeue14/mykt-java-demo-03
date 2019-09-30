package com.leeue.common.exception;


import lombok.Data;

/**
 * @author liyue
 * @date 2019/9/19 11:43
 */
@Data
public class SystemException extends RuntimeException {

    private int code;
    private String msg;

    public SystemException(String msg, int code) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public SystemException(ExceptionEnum exceptionEnum) {
        this.msg = exceptionEnum.getMsg();
        this.code = exceptionEnum.getCode();
    }

    public SystemException(ExceptionEnum exceptionEnum, String msg) {
        this.msg = msg;
        this.code = exceptionEnum.getCode();
    }
}
