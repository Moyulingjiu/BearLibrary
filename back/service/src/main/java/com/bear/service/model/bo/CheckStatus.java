package com.bear.service.model.bo;

/**
 * 打卡状态
 *
 * @author moyulingjiu
 * create 2022年5月8日
 */
public enum CheckStatus {
    /**
     * 通过
     */
    PASS,

    /**
     * 未通过
     */
    REJECT,

    /**
     * 待审核
     */
    WAITING
}
