package com.bear.util;

import lombok.Getter;

/**
 * 统一返回类
 *
 * @author moyulingjiu
 * create 2022年3月26日
 */
@Getter
public class ReturnObject<T> {
    /**
     * 状态码
     */
    private ReturnNo code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 对象数据
     */
    private T data;


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
     * @param code    错误码
     * @param message 自定义message
     */
    public ReturnObject(ReturnNo code, String message) {
        this(code);
        this.message = message;
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
     * @param code    错误码
     * @param message 自定义message
     * @param data    返回值
     */
    public ReturnObject(ReturnNo code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    public String getMessage() {
        if (null != this.message) {
            return this.message;
        } else {
            return this.code.getMessage();
        }
    }
}
