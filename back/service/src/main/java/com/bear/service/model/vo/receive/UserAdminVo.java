package com.bear.service.model.vo.receive;

import com.bear.model.SimplePerson;
import com.bear.service.model.bo.Gender;
import com.bear.util.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * 修改用户自己的信息
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminVo {
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
    @JsonFormat(pattern= Common.DATE_TIME_FORMAT)
    private LocalDateTime birthday;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 荣誉值
     */
    @Range(min = 0)
    private Long honorPoint;

    /**
     * 自律值
     */
    @Range(min = 0)
    private Long selfControlPoint;

    /**
     * 贡献值
     */
    @Range(min = 0)
    private Long contributionPoint;

    /**
     * 行走的经验值
     */
    @Range(min = 0)
    private Long walk;

    /**
     * 阅读的经验值
     */
    @Range(min = 0)
    private Long read;

    /**
     * 运动的经验值
     */
    @Range(min = 0)
    private Long sport;

    /**
     * 艺术的经验值
     */
    @Range(min = 0)
    private Long art;

    /**
     * 实践的经验值
     */
    @Range(min = 0)
    private Long practice;
}
