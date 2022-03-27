package com.bear.service.service;

import com.bear.service.dao.InvitationCodeDao;
import com.bear.service.dao.UserDao;
import com.bear.service.model.bo.InvitationCode;
import com.bear.service.model.bo.User;
import com.bear.service.model.vo.receive.UserRegisterVo;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private InvitationCodeDao invitationCodeDao;

    public User getByName(String name) {
        return userDao.selectByName(name);
    }

    public Object register(UserRegisterVo userRegisterVo) {
        if (userDao.existName(userRegisterVo.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.EXIST_USER_NAME);
        }
        InvitationCode invitationCode = invitationCodeDao.selectByCode(userRegisterVo.getCode());
        return "";
    }
}
