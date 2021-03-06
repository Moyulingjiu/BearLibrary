package com.bear.service.model.bo;

import com.bear.model.SimplePerson;
import com.bear.util.Common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户类
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -2867071904568397169L;

    /**
     * id号
     */
    private Long id;

    /**
     * 微信id，用于微信登陆
     */
    private String openId;

    /**
     * 用户名（登陆用）
     */
    private String name;

    /**
     * 密码，该密码都是加密后的密码
     */
    private String password;

    /**
     * 邀请码id
     */
    private Long invitationCodeId;

    /**
     * 昵称（用于展示）
     */
    private String nickname = "";

    /**
     * 头像对应的url或者base64编码后的图片
     */
    private String avatar = "";

    /**
     * 生日（是日期格式）
     */
    private String birthday = "";

    /**
     * 性别
     */
    private Gender gender = Gender.UNKNOWN;

    /**
     * 电话
     */
    private String phone = "";

    /**
     * 荣誉值
     */
    private Long honorPoint = 0L;

    /**
     * 自律值
     */
    private Long selfControlPoint = 0L;

    /**
     * 贡献值
     */
    private Long contributionPoint = 0L;

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
    private LocalDateTime gmtCreate = LocalDateTime.now();

    /**
     * 创建人
     */
    private SimplePerson create = new SimplePerson();

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified = LocalDateTime.now();

    /**
     * 修改人
     */
    private SimplePerson modified = new SimplePerson();
}
