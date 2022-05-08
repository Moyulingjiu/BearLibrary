package com.bear.service.model.vo.ret;

import com.bear.model.SimplePerson;
import com.bear.service.model.bo.CheckType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 打卡返回类
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRetVo {
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
    private Long point;

    /**
     * 得到的经验值
     */
    private Long exp;

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
    private LocalDateTime gmtCreate;

    /**
     * 创建人
     */
    private SimplePerson create;
}
