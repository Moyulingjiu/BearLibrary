package com.bear.service.dao;

import com.bear.service.mapper.AdministratorPoMapper;
import com.bear.service.model.bo.Administrator;
import com.bear.service.model.po.AdministratorPo;
import com.bear.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 管理员dao层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Repository
public class AdministratorDao {
    @Autowired
    private AdministratorPoMapper administratorPoMapper;

    public Administrator selectById(Long id) {
        AdministratorPo administratorPo = administratorPoMapper.selectByPrimaryKey(id);
        return Common.cloneObject(administratorPo, Administrator.class);
    }
}
