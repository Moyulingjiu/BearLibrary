package com.bear.service.model.bo;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 邀请码
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCode {
    /**
     * id号
     */
    private Long id;

    /**
     * 管理员id
     */
    private Long administratorId;

    /**
     * 使用这个code的用户id
     */
    private Long userId;

    /**
     * 对应的code
     */
    private String code;

    /**
     * 有效期截止的时间
     */
    private LocalDateTime validTime;

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
