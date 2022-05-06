package com.bear.service.service;

import com.bear.model.TokenType;
import com.bear.service.dao.InvitationCodeDao;
import com.bear.service.dao.UserDao;
import com.bear.service.model.bo.InvitationCode;
import com.bear.service.model.bo.User;
import com.bear.service.model.vo.receive.UserLoginVo;
import com.bear.service.model.vo.receive.UserRegisterVo;
import com.bear.service.util.RedisUtils;
import com.bear.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 通过用户名获取用户
     *
     * @param name 用户名
     * @return 用户（如果不存在返回null）
     */
    private User getByName(String name) {
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
        // 检查邀请码是否存在
        if (userDao.existName(userRegisterVo.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.EXIST_USER_NAME);
        }

        // 通过redis，对邀请码进行加锁
        String lockToken = redisUtils.lock(
                RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(),
                DurationTimeUtil.TEN_MINUTE,
                DurationTimeUtil.SECOND
        );
        if (lockToken == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_BE_USED);
        }

        // 检查邀请码是否被其他人使用了
        InvitationCode invitationCode = invitationCodeDao.selectByCode(userRegisterVo.getCode());
        if (invitationCode == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_EXPIRE);
        }
        if (invitationCode.getUserId() != null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_BE_USED);
        }

        // 检查邀请码是否过期
        if (invitationCode.getValidTime().isBefore(LocalDateTime.now())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_EXPIRE);
        }

        // 进行注册，生产user对象
        User user = Common.cloneObject(userRegisterVo, User.class);
        user.setInvitationCodeId(invitationCode.getId());
        int insert = userDao.insert(user);
        if (insert == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }

        // 修改邀请码的使用者
        User userInsert = getByName(user.getName());
        invitationCode.setUserId(userInsert.getId());
        Common.modifyObject(invitationCode, userInsert.getId(), userInsert.getName());
        invitationCodeDao.updateById(invitationCode);

        // 解锁邀请码
        redisUtils.unlock(RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(), lockToken);

        // 生成token
        return ResponseUtil.decorateReturnObject(ReturnNo.CREATED, JwtIssuer.getToken(userInsert.getId(), userInsert.getName(), TokenType.USER));
    }

    /**
     * 登陆
     *
     * @param userLoginVo 登陆数据
     * @return token
     */
    public Object login(UserLoginVo userLoginVo) {
        User user = getByName(userLoginVo.getName());
        if (user == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        if (!userLoginVo.getPassword().equals(user.getPassword())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, JwtIssuer.getToken(user.getId(), user.getName(), TokenType.USER));
    }
}
