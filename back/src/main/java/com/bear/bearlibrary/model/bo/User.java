package com.bear.bearlibrary.model.bo;

import com.bear.bearlibrary.model.bo.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户实体类
 *
 * @author 墨羽翎玖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String password;
    private Long invitationCodeId;
    private String nickname;
    private String realname;
    private String avatar;
    private String birthday;
    private Gender gender;
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
    private LocalDateTime gmtCreate;
    private Long createId;
    private LocalDateTime gmtModified;
    private Long modifiedId;
}
