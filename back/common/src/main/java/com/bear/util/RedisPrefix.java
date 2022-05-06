package com.bear.util;

/**
 * redis 字段前缀集
 *
 * @author moyulingjiu
 */
public class RedisPrefix {
    /**
     * 用户名是否已经存在
     */
    public static final String USER_NAME_EXIST = "user_name_exist_";

    /**
     * 管理员名是否已经存在
     */
    public static final String ADMIN_NAME_EXIST = "admin_name_exist_";

    /**
     * 注册时锁住某一个token
     */
    public static final String REGISTER_TOKEN_COVER = "register_token_cover_";

    /**
     * 用户数据
     */
    public static final String USER = "user_";

    /**
     * 管理员数据
     */
    public static final String ADMIN = "admin_";
}
