package com.bear.util;

/**
 * 统一返回编号
 *
 * @author moyulingjiu
 * create 2022年3月26日
 */
public enum ReturnNo {
    /**
     * 成功
     */
    OK(200, "成功"),
    CREATED(201, "创建成功"),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERR(500, "服务器内部错误");

    private final int code;
    private final String message;

    ReturnNo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
