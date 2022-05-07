package com.bear.service.model.vo.ret;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 管理员返回值
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRetVo {
    /**
     * id号
     */
    private Long id;

    /**
     * 用户名（登陆用）
     */
    private String name;

    /**
     * 管理员是否有效
     */
    private Integer valid;


    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 创建人
     */
    private SimplePerson create;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 修改人
     */
    private SimplePerson modified;
}
