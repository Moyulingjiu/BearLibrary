package com.bear.service.model.vo.ret;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 查看贡献
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContributionRetVo {
    /**
     * id号
     */
    private Long id;

    /**
     * 管理员id
     */
    private Long administratorId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 贡献点
     */
    private Long point = 0L;

    /**
     * 备注
     */
    private String comment;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate = LocalDateTime.now();

    /**
     * 创建人
     */
    private SimplePerson create;
}
