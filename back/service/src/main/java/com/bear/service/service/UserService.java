package com.bear.service.service;

import com.bear.service.dao.InvitationCodeDao;
import com.bear.service.dao.UserDao;
import com.bear.service.model.bo.InvitationCode;
import com.bear.service.model.bo.User;
import com.bear.service.model.vo.receive.UserRegisterVo;
import com.bear.util.Common;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 注册
     *
     * @param userRegisterVo 用户注册信息类
     * @return 注册的结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Object register(UserRegisterVo userRegisterVo) {
        if (userDao.existName(userRegisterVo.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.EXIST_USER_NAME);
        }
        // todo: 通过redis锁code（避免高并发同时注册遇见问题）
        InvitationCode invitationCode = invitationCodeDao.selectByCode(userRegisterVo.getCode());
        if (invitationCode.getUserId() != null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_BE_USED);
        }
        User user = Common.cloneObject(userRegisterVo, User.class);
        user.setInvitationCodeId(invitationCode.getId());
        int insert = userDao.insert(user);
        if (insert == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        User userInsert = getByName(user.getName());
        invitationCode.setUserId(userInsert.getId());
        invitationCodeDao.updateById(invitationCode);

        return "";
    }
}
