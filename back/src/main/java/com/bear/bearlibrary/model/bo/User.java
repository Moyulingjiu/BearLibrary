package com.bear.bearlibrary.model.bo;

import com.bear.bearlibrary.model.bo.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
