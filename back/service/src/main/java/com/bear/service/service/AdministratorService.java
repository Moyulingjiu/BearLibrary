package com.bear.service.service;

import com.bear.encript.Aes;
import com.bear.model.SimplePerson;
import com.bear.model.TokenType;
import com.bear.service.dao.AdministratorDao;
import com.bear.service.model.bo.Administrator;
import com.bear.service.model.vo.receive.AdminCreateVo;
import com.bear.service.model.vo.receive.AdminLoginVo;
import com.bear.service.util.StringUtils;
import com.bear.util.Common;
import com.bear.util.JwtIssuer;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员service
 *
 * @author moyulingjiu
 */
@Service
public class AdministratorService {
    @Autowired
    AdministratorDao administratorDao;

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
    public Object create(AdminCreateVo adminCreateVo, Long id, String name) {
        if (administratorDao.existName(adminCreateVo.getName())) {
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
}
