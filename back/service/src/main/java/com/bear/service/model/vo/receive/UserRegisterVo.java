package com.bear.service.model.vo.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull
    private String code;
    private String name;
    private String password;
}
