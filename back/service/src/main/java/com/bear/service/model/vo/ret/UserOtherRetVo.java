package com.bear.service.model.vo.ret;

import com.bear.service.model.bo.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查看其他用户的时候
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOtherRetVo {
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
     * 生日（是日期格式）
     */
    private String birthday;

    /**
     * 性别
     */
    private Gender gender = Gender.UNKNOWN;

    /**
     * 电话
     */
    private String phone;

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
}
