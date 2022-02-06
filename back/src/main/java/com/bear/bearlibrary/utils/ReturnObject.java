package com.bear.bearlibrary.utils;

import lombok.Getter;

/**
 * 统一返回类
 *
 * @param <T>
 */
@Getter
public class ReturnObject<T> {
    /**
     * 错误号
     */
    private ReturnNo code = ReturnNo.OK;

    /**
     * 自定义的错误码
     */
    private String msg = null;

    /**
     * 返回值
     */
    private T data = null;

    /**
     * 默认构造函数，错误码为OK
     */
    public ReturnObject() {
    }

    /**
     * 带值构造函数
     *
     * @param data 返回值
     */
    public ReturnObject(T data) {
        this();
        this.data = data;
    }

    /**
     * 有错误码的构造函数
     *
     * @param code 错误码
     */
    public ReturnObject(ReturnNo code) {
        this.code = code;
    }

    /**
     * 有错误码和自定义message的构造函数
     *
     * @param code 错误码
     * @param msg  自定义message
     */
    public ReturnObject(ReturnNo code, String msg) {
        this(code);
        this.msg = msg;
    }

    /**
     * 有错误码，自定义message和值的构造函数
     *
     * @param code 错误码
     * @param data 返回值
     */
    public ReturnObject(ReturnNo code, T data) {
        this(code);
        this.data = data;
    }


    /**
     * 有错误码，自定义message和值的构造函数
     *
     * @param code 错误码
     * @param msg  自定义message
     * @param data 返回值
     */
    public ReturnObject(ReturnNo code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    /**
     * 错误信息
     * <p>对于不存在的错误信息，返回code对应的错误信息</p>
     *
     * @return 错误信息
     */
    public String getMsg() {
        if (null != this.msg) {
            return this.msg;
        } else {
            return this.code.getMessage();
        }
    }
}
