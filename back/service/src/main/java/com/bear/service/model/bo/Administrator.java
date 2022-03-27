package com.bear.service.model.bo;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 管理员类
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
    /**
     * id号
     */
    private Long id;

    /**
     * 用户名（登陆用）
     */
    private String name;

    /**
     * 密码，该密码都是加密后的密码
     */
    private String password;

    /**
     * 管理员是否有效
     */
    private Integer valid = 1;

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
