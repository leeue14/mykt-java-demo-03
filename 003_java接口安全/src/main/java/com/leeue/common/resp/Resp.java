package com.leeue.common.resp;

import com.leeue.common.exception.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liyue
 * @date 2019/9/19 11:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Resp<T> implements Serializable {

    private int code;

    private String msg;

    private T data;


    private Resp(T data) {
        super();
        this.code = 200;
        this.msg = "ok";
        this.data = data;
    }

    private Resp(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = 500;
        this.data = null;
    }

    private Resp(Throwable e, int code) {
        super();
        this.msg = e.getMessage();
        this.code = code;
        this.data = null;
    }

    private Resp(String msg, int code) {
        super();
        this.msg = msg;
        this.code = code;
        this.data = null;
    }


    public static <T> Resp<T> error(ExceptionEnum exceptionEnum, String extMsg) {

        return new Resp<>(exceptionEnum.getMsg() + "（" + extMsg + "）", exceptionEnum.getCode());
    }

    public static <T> Resp<T> error(String msg, int code) {

        return new Resp<>(msg, code);
    }

    public static <T> Resp<T> ok(T data) {
        return new Resp(data);
    }
}
