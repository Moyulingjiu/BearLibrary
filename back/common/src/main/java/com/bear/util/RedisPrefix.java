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

    /**
     * 邀请码数据
     */
    public static final String INVITATION_CODE = "invitation_code_";

    /**
     * 消息数据
     */
    public static final String MESSAGE = "message_";

    /**
     * 贡献
     */
    public static final String CONTRIBUTION = "contribution_";

    /**
     * 打卡
     */
    public static final String CHECK = "check";

    /**
     * 锁住用户
     */
    public static final String USER_LOCK = "user_lock_";

    /**
     * 锁住打卡
     */
    public static final String CHECK_LOCK = "check_lock_";

    /**
     * 锁住贡献
     */
    public static final String CONTRIBUTION_LOCK = "contribution_lock_";
}
