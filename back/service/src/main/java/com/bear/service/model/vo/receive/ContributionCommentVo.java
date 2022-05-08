package com.bear.service.model.vo.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 修改贡献的备注
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContributionCommentVo {
    @NotBlank(message = "贡献备注不能为空")
    @Length(max = 200, message = "贡献备注长度不能超过200")
    private String comment;
}
