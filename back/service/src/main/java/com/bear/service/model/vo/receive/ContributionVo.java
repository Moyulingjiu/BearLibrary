package com.bear.service.model.vo.receive;

import com.bear.model.SimplePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 贡献的vo
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContributionVo {
    /**
     * 用户id
     */
    @NotNull
    private Long userId;

    /**
     * 贡献点
     */
    @NotNull
    @Range(min = 0)
    private Long point;

    /**
     * 备注
     */
    private String comment;
}
