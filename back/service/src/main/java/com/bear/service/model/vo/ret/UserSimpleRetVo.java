package com.bear.service.model.vo.ret;

import com.bear.service.model.bo.Gender;
import com.bear.util.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户类
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleRetVo {
    /**
     * id号
     */
    private Long id;

    /**
     * 用户名（登陆用）
     */
    private String name;

    /**
     * 昵称（用于展示）
     */
    private String nickname;

    /**
     * 头像对应的url或者base64编码后的图片
     */
    private String avatar;

    /**
     * 性别
     */
    private Gender gender = Gender.UNKNOWN;

    /**
     * 行走的经验值
     */
    private Long walk = 0L;

    /**
     * 阅读的经验值
     */
    private Long read = 0L;

    /**
     * 运动的经验值
     */
    private Long sport = 0L;

    /**
     * 艺术的经验值
     */
    private Long art = 0L;

    /**
     * 实践的经验值
     */
    private Long practice = 0L;

    /**
     * 用户是否有效
     */
    private Integer valid = 1;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = Common.DATE_TIME_FORMAT, timezone = "GMT+8")
    private LocalDateTime gmtCreate;
}
