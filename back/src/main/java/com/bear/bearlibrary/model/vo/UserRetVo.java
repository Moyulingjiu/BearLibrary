package com.bear.bearlibrary.model.vo;

import com.bear.bearlibrary.model.bo.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询用户返回信息
 *
 * @author 墨羽翎玖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRetVo {
    private Long id;
    private String password;
    private Long invitationCodeId;
    private String nickname;
    private String realname;
    private String avatar;
    private String birthday;
    private Integer gender;
    private String phone;
    private Long honorPoint;
    private Long selfControlPoint;
    private Long contributionPoint;
    private Long walk;
    private Long read;
    private Long sport;
    private Long art;
    private Long practice;
    private Integer valid;
}
