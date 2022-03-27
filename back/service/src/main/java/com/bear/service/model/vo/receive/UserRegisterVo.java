package com.bear.service.model.vo.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 注册的用户vo
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterVo {
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
