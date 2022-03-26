package com.bear.model;

/**
 * Vo对象的抽象接口
 *
 * @author moyulingjiu
 * create 2022年3月26日21:43:24
 */
public interface VoObject {
    /**
     * 创建Vo对象
     * @return Vo对象
     */
    public Object createVo();

    /**
     * 创建简单Vo对象
     * @return 简单Vo对象
     */
    public Object createSimpleVo();
}
