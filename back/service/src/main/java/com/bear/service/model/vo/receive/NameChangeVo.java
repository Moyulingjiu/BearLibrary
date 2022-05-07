package com.bear.service.model.vo.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户名修改
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameChangeVo {
    @NotBlank
    private String name;
}
