package com.leeue.common.exception;

/**
 * @author liyue
 * @date 2019/9/19 11:49
 */

public enum ExceptionEnum {

    PARAM_ERROR(500, "参数异常。");


    private int code;
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
