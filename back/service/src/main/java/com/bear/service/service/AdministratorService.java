package com.bear.service.service;

import com.bear.encript.Aes;
import com.bear.model.Page;
import com.bear.model.SimplePerson;
import com.bear.model.TokenType;
import com.bear.service.dao.AdministratorDao;
import com.bear.service.dao.UserDao;
import com.bear.service.model.bo.Administrator;
import com.bear.service.model.vo.receive.AdminCreateVo;
import com.bear.service.model.vo.receive.AdminLoginVo;
import com.bear.service.model.vo.receive.NameChangeVo;
import com.bear.service.model.vo.receive.PasswordChangeVo;
import com.bear.service.model.vo.ret.AdminRetVo;
import com.bear.service.util.StringUtils;
import com.bear.util.Common;
import com.bear.util.JwtIssuer;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员service
 *
 * @author moyulingjiu
 */
@Service
public class AdministratorService {
    @Autowired
    AdministratorDao administratorDao;
    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public Administrator getByName(String name) {
        return administratorDao.selectByName(name);
    }

    /**
     * 登陆管理员
     *
     * @param adminLoginVo 登陆信息
     * @return token
     */
    @Transactional(rollbackFor = Exception.class)
    public Object login(AdminLoginVo adminLoginVo) {
        Administrator admin = getByName(adminLoginVo.getName());
        if (admin == null || !StringUtils.validPassword(adminLoginVo.getPassword())) {
            // 如果系统还没有初始化root用户，则初始化
            if (adminLoginVo.getName().equals(Common.INIT_USER_NAME) && adminLoginVo.getPassword().equals(Common.INIT_PASSWORD)) {
                Administrator root = administratorDao.selectById(1L);
                if (root == null) {
                    Administrator administrator = new Administrator();
                    administrator.setName(Common.INIT_USER_NAME);
                    administrator.setPassword(Aes.encrypt(Common.INIT_PASSWORD, Common.getPasswordSecret()));
                    int insert = administratorDao.insert(administrator);
                    if (insert != 1) {
                        return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
                    }
                    return ResponseUtil.decorateReturnObject(ReturnNo.OK, JwtIssuer.getToken(1L, administrator.getName(), TokenType.ADMIN));
                }
            }
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        String password = Aes.decrypt(admin.getPassword(), Common.getPasswordSecret());
        if (!adminLoginVo.getPassword().equals(password)) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, JwtIssuer.getToken(admin.getId(), admin.getName(), TokenType.ADMIN));
    }

    /**
     * 创建管理员
     *
     * @param adminCreateVo 创建管理员
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Object create(AdminCreateVo adminCreateVo, Long id, String name) {
        // 检查名字、密码是否合法
        if (administratorDao.existName(adminCreateVo.getName()) || userDao.existName(adminCreateVo.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.EXIST_USER_NAME);
        }
        if (!StringUtils.validPassword(adminCreateVo.getPassword())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_FORMAT);
        }
        if (!StringUtils.validUserName(adminCreateVo.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_USER_NAME_FORMAT);
        }

        Administrator administrator = Common.cloneObject(adminCreateVo, Administrator.class);
        administrator.setPassword(Aes.encrypt(administrator.getPassword(), Common.getPasswordSecret()));
        administrator.setCreate(new SimplePerson(id, name));
        administrator.setModified(new SimplePerson(id, name));
        int insert = administratorDao.insert(administrator);
        if (insert <= 0) {
            ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.CREATED);
    }

    /**
     * 修改密码
     *
     * @param passwordChangeVo 密码
     * @param adminId          管理员id
     * @param adminName        管理员名
     * @return 状态
     */
    @Transactional(rollbackFor = Exception.class)
    public Object changePassword(PasswordChangeVo passwordChangeVo, Long adminId, String adminName) {
        Administrator admin = getByName(adminName);
        if (admin == null || admin.getValid() == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        String password = Aes.decrypt(admin.getPassword(), Common.getPasswordSecret());
        if (!passwordChangeVo.getOldPassword().equals(password)) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_USER_NAME);
        }
        if (!StringUtils.validPassword(passwordChangeVo.getNewPassword())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_PASSWORD_FORMAT);
        }
        if (passwordChangeVo.getNewPassword().equals(passwordChangeVo.getOldPassword())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.SAME_PASSWORD);
        }
        admin.setPassword(Aes.encrypt(passwordChangeVo.getNewPassword(), Common.getPasswordSecret()));
        Common.modifyObject(admin, adminId, adminName);
        int update = administratorDao.update(admin);
        if (update <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }

    /**
     * 修改用户名
     *
     * @param name      名字
     * @param adminId   管理员id
     * @param adminName 管理员名
     * @return 状态
     */
    @Transactional(rollbackFor = Exception.class)
    public Object changeName(NameChangeVo name, Long adminId, String adminName) {
        Administrator administrator = administratorDao.selectById(adminId);
        if (name.getName().equals(administrator.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.SAME_USER_NAME);
        }
        if (administratorDao.existName(name.getName()) || userDao.existName(name.getName())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.EXIST_USER_NAME);
        }
        administrator.setName(name.getName());
        Common.modifyObject(administrator, adminId, adminName);
        int update = administratorDao.update(administrator);
        if (update <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }

    /**
     * 删除管理员
     *
     * @param id        被删除的管理员id
     * @param adminId   管理员id
     * @param adminName 管理员名
     * @return 状态
     */
    @Transactional(rollbackFor = Exception.class)
    public Object delete(Long id, Long adminId, String adminName) {
        Administrator administrator = administratorDao.selectById(id);
        if (administrator == null || administrator.getValid() == 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        administrator.setValid(0);
        Common.modifyObject(administrator, adminId, adminName);
        int update = administratorDao.update(administrator);
        if (update <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }

    /**
     * 通过id获取管理员
     *
     * @param id 管理员id
     * @return 管理员
     */
    @Transactional(rollbackFor = Exception.class)
    public Object get(Long id) {
        Administrator administrator = administratorDao.selectById(id);
        if (administrator == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        AdminRetVo adminRetVo = Common.cloneObject(administrator, AdminRetVo.class);
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, adminRetVo);
    }

    /**
     * 获取所有管理员
     *
     * @param page      页码
     * @param pageSize  页面大小
     * @param name      管理员名
     * @param valid     是否有效
     * @param beginTime 创建时间开始
     * @param endTime   创建时间结束
     * @return 管理员列表
     */
    @Transactional(rollbackFor = Exception.class)
    public Object getAll(Integer page, Integer pageSize, String name, Integer valid, LocalDateTime beginTime, LocalDateTime endTime) {
        Page<Administrator> administratorPage = administratorDao.selectAll(page, pageSize, name, valid, beginTime, endTime);
        List<Administrator> administrators = administratorPage.getList();
        ArrayList<AdminRetVo> adminRetVos = new ArrayList<>();
        for (Administrator administrator : administrators) {
            AdminRetVo adminRetVo = Common.cloneObject(administrator, AdminRetVo.class);
            adminRetVos.add(adminRetVo);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, new Page<>(adminRetVos, administratorPage));
    }
}
