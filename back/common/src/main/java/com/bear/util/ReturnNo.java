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
    INTERNAL_SERVER_ERR(500, "服务器内部错误"),

    /**
     * 操作相关的错误
     */
    RESOURCE_NOT_EXIST(404, "请求的资源不存在"),
    FORBIDDEN(403, "无权访问请求的资源"),
    NOT_LOGIN(401, "未登录，或者token不合法"),
    ILLEGAL_REQUEST(405, "不合法的请求，请检查请求字段"),

    /**
     * 注册的错误
     */
    EXIST_USER_NAME(700, "用户名已存在"),
    CODE_BE_USED(701, "邀请码已经被使用过了"),
    ILLEGAL_PASSWORD_FORMAT(702, "密码的格式不正确"),
    ILLEGAL_USER_NAME_FORMAT(703, "用户名的格式不正确"),
    CODE_EXPIRE(704, "邀请码已经过期或不存在"),

    /**
     * 登陆的错误
     */
    ILLEGAL_PASSWORD_USER_NAME(801, "错误的用户名或密码"),
    NEED_CHANGE_TOKEN(802, "需要更新token");

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
