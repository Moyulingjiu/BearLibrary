package com.bear.service.dao;

import com.bear.service.mapper.AdministratorPoMapper;
import com.bear.service.model.bo.Administrator;
import com.bear.service.model.bo.User;
import com.bear.service.model.po.AdministratorPo;
import com.bear.service.model.po.AdministratorPoExample;
import com.bear.service.model.po.UserPoExample;
import com.bear.service.util.RedisUtils;
import com.bear.service.util.StringUtils;
import com.bear.util.Common;
import com.bear.util.RedisPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Autowired
    private RedisUtils redisUtils;

    public Administrator selectById(Long id) {
        Object obj = redisUtils.getObj(RedisPrefix.ADMIN + id);
        if (obj != null) {
            return (Administrator) obj;
        }
        AdministratorPo administratorPo = administratorPoMapper.selectByPrimaryKey(id);
        Administrator administrator = Common.cloneObject(administratorPo, Administrator.class);
        redisUtils.putObj(RedisPrefix.ADMIN + id, administrator);
        return administrator;
    }

    public boolean existName(String name) {
        String s = redisUtils.get(RedisPrefix.ADMIN_NAME_EXIST + name);
        if (StringUtils.isNotEmpty(s)) {
            return true;
        }
        return selectByName(name) != null;
    }

    public Administrator selectByName(String name) {
        Object obj = redisUtils.getObj(RedisPrefix.ADMIN + name);
        if (obj != null) {
            return (Administrator) obj;
        }
        AdministratorPoExample example = new AdministratorPoExample();
        AdministratorPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andValidEqualTo(1);
        List<AdministratorPo> administratorPos = administratorPoMapper.selectByExample(example);
        if (administratorPos.size() == 0) {
            return  null;
        }
        redisUtils.put(RedisPrefix.ADMIN_NAME_EXIST + name, "1");
        Administrator administrator = Common.cloneObject(administratorPos.get(0), Administrator.class);
        redisUtils.putObj(RedisPrefix.ADMIN + name, administrator);
        return administrator;
    }

    public int insert(Administrator administrator) {
        AdministratorPo administratorPo = Common.cloneObject(administrator, AdministratorPo.class);
        return administratorPoMapper.insert(administratorPo);
    }
}
