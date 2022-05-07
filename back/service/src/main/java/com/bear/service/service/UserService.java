package com.bear.service.service;

import com.bear.encript.Aes;
import com.bear.model.Page;
import com.bear.model.TokenType;
import com.bear.service.dao.AdministratorDao;
import com.bear.service.dao.InvitationCodeDao;
import com.bear.service.dao.UserDao;
import com.bear.service.model.bo.Gender;
import com.bear.service.model.bo.InvitationCode;
import com.bear.service.model.bo.User;
import com.bear.service.model.vo.receive.PasswordChangeVo;
import com.bear.service.model.vo.receive.UserLoginVo;
import com.bear.service.model.vo.receive.UserRegisterVo;
import com.bear.service.model.vo.receive.UserVo;
import com.bear.service.model.vo.ret.UserOtherRetVo;
import com.bear.service.model.vo.ret.UserRetVo;
import com.bear.service.model.vo.ret.UserSimpleRetVo;
import com.bear.service.util.RedisUtils;
import com.bear.service.util.StringUtils;
import com.bear.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private AdministratorDao administratorDao;
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
    @Transactional(rollbackFor = Exception.class)
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
        // 检查名字是否已经被人使用
        if (userDao.existName(userRegisterVo.getName()) || administratorDao.existName(userRegisterVo.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.EXIST_USER_NAME);
        }

        // 通过redis，对邀请码进行加锁
        String lockToken = redisUtils.lock(
                RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(),
                DurationTimeUtil.HALF_MINUTE,
                DurationTimeUtil.SECOND
        );
        if (lockToken == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_BE_USED);
        }

        // 检查邀请码是否被其他人使用了
        InvitationCode invitationCode = invitationCodeDao.selectByCode(userRegisterVo.getCode());
        if (invitationCode == null) {
            redisUtils.unlock(RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(), lockToken);
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_EXPIRE);
        }
        if (invitationCode.getUserId() != null) {
            redisUtils.unlock(RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(), lockToken);
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_BE_USED);
        }

        // 检查邀请码是否过期
        if (invitationCode.getValidTime() == null || invitationCode.getValidTime().isBefore(LocalDateTime.now())) {
            redisUtils.unlock(RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(), lockToken);
            return ResponseUtil.decorateReturnObject(ReturnNo.CODE_EXPIRE);
        }

        // 检查密码和用户名是否合法
        if (!StringUtils.validUserName(userRegisterVo.getName())) {
            redisUtils.unlock(RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(), lockToken);
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_USER_NAME_FORMAT);
        }
        if (!StringUtils.validPassword(userRegisterVo.getPassword())) {
            redisUtils.unlock(RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(), lockToken);
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_FORMAT);
        }

        // 进行注册，生产user对象
        User user = Common.cloneObject(userRegisterVo, User.class);
        user.setPassword(Aes.encrypt(user.getPassword(), Common.getPasswordSecret()));
        user.setInvitationCodeId(invitationCode.getId());
        user.setCreate(invitationCode.getCreate());
        user.setModified(invitationCode.getCreate());
        int insert = userDao.insert(user);
        if (insert == 0) {
            redisUtils.unlock(RedisPrefix.REGISTER_TOKEN_COVER + userRegisterVo.getCode(), lockToken);
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
    @Transactional(rollbackFor = Exception.class)
    public Object login(UserLoginVo userLoginVo) {
        User user = getByName(userLoginVo.getName());
        if (user == null || !StringUtils.validPassword(userLoginVo.getPassword())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        String password = Aes.decrypt(user.getPassword(), Common.getPasswordSecret());
        if (!userLoginVo.getPassword().equals(password)) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, JwtIssuer.getToken(user.getId(), user.getName(), TokenType.USER));
    }

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户数据
     */
    @Transactional(rollbackFor = Exception.class)
    public Object get(Long id) {
        User user = userDao.selectById(id);
        if (user == null || user.getValid() == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        UserRetVo userRetVo = Common.cloneObject(user, UserRetVo.class);
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, userRetVo);
    }

    /**
     * 获取其他用户信息
     *
     * @param id 用户id
     * @return 用户数据
     */
    @Transactional(rollbackFor = Exception.class)
    public Object getOther(Long id) {
        User user = userDao.selectById(id);
        if (user == null || user.getValid() == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        UserOtherRetVo otherRetVo = Common.cloneObject(user, UserOtherRetVo.class);
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, otherRetVo);
    }

    /**
     * 管理员查看用户数据
     *
     * @param id 用户id
     * @return 用户数据
     */
    @Transactional(rollbackFor = Exception.class)
    public Object getByAdmin(Long id) {
        User user = userDao.selectById(id);
        if (user == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        UserRetVo userRetVo = Common.cloneObject(user, UserRetVo.class);
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, userRetVo);
    }

    /**
     * 管理员分页条件查询用户
     *
     * @param page        页码
     * @param pageSize    页面大小
     * @param name        用户名
     * @param valid       是否没有被删除
     * @param gender      性别
     * @param phone       电话
     * @param nickname    昵称
     * @param beginTime   创建时间下限
     * @param endTime     创建时间上限
     * @param minWalk     行走经验下限
     * @param maxWalk     行走经验上限
     * @param minRead     阅读经验下限
     * @param maxRead     阅读经验上限
     * @param minSport    运动经验下限
     * @param maxSport    运动经验上限
     * @param minArt      艺术经验下限
     * @param maxArt      艺术经验上限
     * @param minPractice 实践经验下限
     * @param maxPractice 实践经验上限
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Object getAllByAdmin(
            Integer page,
            Integer pageSize,
            String name,
            Integer valid,
            Integer gender,
            String phone,
            String nickname,
            LocalDateTime beginTime,
            LocalDateTime endTime,
            Long minWalk,
            Long maxWalk,
            Long minRead,
            Long maxRead,
            Long minSport,
            Long maxSport,
            Long minArt,
            Long maxArt,
            Long minPractice,
            Long maxPractice
    ) {
        Page<User> userPage = userDao.selectAll(page, pageSize, name, valid, gender, phone, nickname, beginTime, endTime, minWalk, maxWalk, minRead, maxRead, minSport, maxSport, minArt, maxArt, minPractice, maxPractice);
        List<User> userList = userPage.getList();
        List<UserSimpleRetVo> userSimpleRetVoList = new ArrayList<>();
        for (User user : userList) {
            userSimpleRetVoList.add(Common.cloneObject(user, UserSimpleRetVo.class));
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, new Page<>(userSimpleRetVoList, userPage));
    }

    /**
     * 修改用户密码
     *
     * @param passwordChangeVo 密码
     * @param id               用户id
     * @param name             用户名
     * @return 状态
     */
    @Transactional(rollbackFor = Exception.class)
    public Object changePassword(PasswordChangeVo passwordChangeVo, Long id, String name) {
        User user = getByName(name);
        if (user == null || user.getValid() == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        String password = Aes.decrypt(user.getPassword(), Common.getPasswordSecret());
        if (!passwordChangeVo.getOldPassword().equals(password)) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        if (!StringUtils.validPassword(passwordChangeVo.getNewPassword())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_FORMAT);
        }
        if (passwordChangeVo.getNewPassword().equals(passwordChangeVo.getOldPassword())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.SAME_PASSWORD);
        }
        user.setPassword(Aes.encrypt(passwordChangeVo.getNewPassword(), Common.getPasswordSecret()));
        Common.modifyObject(user, id, name);
        int update = userDao.update(user);
        if (update <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }

    /**
     * 改变用户信息
     *
     * @param userVo 用户信息
     * @param id     id
     * @param name   name
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Object changeUser(UserVo userVo, Long id, String name) {
        User user = userDao.selectById(id);
        if (user == null || user.getValid() == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        Common.modifyObject(user, id, name);
        if (userVo.getNickname() != null) {
            user.setNickname(userVo.getNickname());
        }
        if (userVo.getAvatar() != null) {
            user.setAvatar(userVo.getAvatar());
        }
        if (userVo.getBirthday() != null) {
            DateTimeFormatter dfDateTime = DateTimeFormatter.ofPattern(Common.DATE_TIME_FORMAT);
            user.setBirthday(dfDateTime.format(userVo.getBirthday()));
        }
        if (userVo.getPhone() != null) {
            user.setPhone(userVo.getPhone());
        }
        if (userVo.getGender() != null) {
            Gender[] values = Gender.values();
            if (userVo.getGender() >= 0 && userVo.getGender() < values.length) {
                user.setGender(values[userVo.getGender()]);
            }
        }
        int update = userDao.update(user);
        if (update <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Object delete(Long id, Long adminId, String adminName) {
        User user = userDao.selectById(id);
        if (user == null || user.getValid() == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        user.setValid(0);
        Common.modifyObject(user, adminId, adminName);
        int update = userDao.update(user);
        if (update <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }
}
