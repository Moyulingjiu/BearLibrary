package com.bear.service.model.bo;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 打卡类
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Check implements Serializable {
    private static final long serialVersionUID = -2867071904568397169L;
    /**
     * id号
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 审核的管理员id
     */
    private Long administratorId;

    /**
     * 打卡状态
     */
    private CheckStatus status;

    /**
     * 打卡的类别
     */
    private CheckType type;

    /**
     * 图片链接
     */
    private String url;

    /**
     * 得到的点数
     * 对于学习进步目标，对应自律值
     * 其他对应荣誉值
     */
    private Long point = 0L;

    /**
     * 得到的经验值
     */
    private Long exp = 0L;

    /**
     * 管理员备注信息
     */
    private String adminComment;

    /**
     * 用户备注信息
     */
    private String userComment;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate = LocalDateTime.now();

    /**
     * 创建人
     */
    private SimplePerson create;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified = LocalDateTime.now();

    /**
     * 修改人
     */
    private SimplePerson modified;
}
