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
}
