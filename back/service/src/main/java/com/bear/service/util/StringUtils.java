package com.bear.service.util;

/**
 * 字符串工具类
 *
 * @author moyulingjiu
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 检查密码的合法性
     *
     * @param password 密码
     * @return 是否合法
     */
    public static boolean validPassword(String password) {
        String regex = "^[a-zA-Z\\d,.!]{5,16}$";
        return password.matches(regex);
    }

    /**
     * 检查用户名的合法性
     *
     * @param name 用户名
     * @return 是否合法
     */
    public static boolean validUserName(String name) {
        String regex = "^[a-zA-Z][a-zA-z\\d]{3,9}$";
        return name.matches(regex);
    }
}
