package com.bear.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回类
 *
 * @author moyulingjiu
 */
public class ResponseUtil {
    /**
     * 状态码
     */
    private static final String CODE = "code";
    /**
     * 消息
     */
    private static final String MESSAGE = "msg";
    /**
     * 数据
     */
    private static final String DATA = "data";
    /**
     * 时间戳
     */
    private static final String TIMESTAMP = "timestamp";

    /**
     * 装饰data对象
     *
     * @param returnNo 返回号
     * @param data     数据
     * @return map
     */
    public static Object decorateReturnObject(ReturnNo returnNo, Object data) {
        return decorateReturnObject(new ReturnObject<>(returnNo, data));
    }

    /**
     * 装饰返回对象
     *
     * @param returnObject 返回对象
     * @return map
     */
    public static Object decorateReturnObject(ReturnObject<?> returnObject) {
        Map<String, Object> obj = new HashMap<>(3);
        obj.put(CODE, returnObject.getCode().getCode());
        obj.put(MESSAGE, returnObject.getMessage());
        obj.put(DATA, returnObject.getData());
        obj.put(TIMESTAMP, Common.getTimestamp());
        return obj;
    }

    /**
     * 装饰返回对象
     *
     * @param returnNo 返回状态码
     * @return map
     */
    public static Object decorateReturnObject(ReturnNo returnNo) {
        Map<String, Object> obj = new HashMap<>(3);
        obj.put(CODE, returnNo.getCode());
        obj.put(MESSAGE, returnNo.getMessage());
        obj.put(DATA, null);
        obj.put(TIMESTAMP, Common.getTimestamp());
        return obj;
    }

    /**
     * 返回默认对象
     *
     * @return map
     */
    public static Object decorateReturnObject() {
        return decorateReturnObject(ReturnNo.OK);
    }

    /**
     * 对于返回boolean类型成功的请求
     *
     * @return true
     */
    public static Object success() {
        Map<String, Object> obj = new HashMap<>(3);
        obj.put(CODE, ReturnNo.OK.getCode());
        obj.put(MESSAGE, ReturnNo.OK.getMessage());
        obj.put(DATA, true);
        obj.put(TIMESTAMP, Common.getTimestamp());
        return obj;
    }

    /**
     * 对于返回boolean类型成功的请求
     *
     * @return false
     */
    public static Object fail() {
        Map<String, Object> obj = new HashMap<>(3);
        obj.put(CODE, ReturnNo.OK.getCode());
        obj.put(MESSAGE, ReturnNo.OK.getMessage());
        obj.put(DATA, false);
        obj.put(TIMESTAMP, Common.getTimestamp());
        return obj;
    }

    /**
     * 无效的token
     *
     * @return map
     */
    public static Object badToken() {
        return decorateReturnObject(ReturnNo.NOT_LOGIN);
    }

    /**
     * 拒绝访问
     *
     * @return map
     */
    public static Object deny() {
        return decorateReturnObject(ReturnNo.FORBIDDEN);
    }

}
