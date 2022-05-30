package com.bear.service.dao;

import com.bear.model.Page;
import com.bear.service.mapper.AdministratorPoMapper;
import com.bear.service.model.bo.Administrator;
import com.bear.service.model.po.AdministratorPo;
import com.bear.service.model.po.AdministratorPoExample;
import com.bear.service.util.RedisUtils;
import com.bear.service.util.StringUtils;
import com.bear.util.Common;
import com.bear.util.RedisPrefix;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            redisUtils.putObj(RedisPrefix.ADMIN + name, null);
            return null;
        }
        Administrator administrator = null;
        for (AdministratorPo administratorPo : administratorPos) {
            if (administratorPo.getName().equals(name)) {
                administrator = Common.cloneObject(administratorPo, Administrator.class);
            }
        }
        if (administrator == null) {
            redisUtils.putObj(RedisPrefix.ADMIN + name, null);
            return null;
        }
        redisUtils.put(RedisPrefix.ADMIN_NAME_EXIST + name, "1");
        redisUtils.putObj(RedisPrefix.ADMIN + name, administrator);
        return administrator;
    }

    public long insert(Administrator administrator) {
        AdministratorPo administratorPo = Common.cloneObject(administrator, AdministratorPo.class);
        int insert = administratorPoMapper.insert(administratorPo);
        if (insert == 0 || administratorPo.getId() == null) {
            return 0L;
        }
        redisUtils.deleteKey(RedisPrefix.ADMIN + administrator.getName());
        redisUtils.deleteKey(RedisPrefix.ADMIN + administratorPo.getId());
        return administratorPo.getId();
    }

    public int update(Administrator administrator) {
        Administrator origin = selectById(administrator.getId());
        AdministratorPo administratorPo = Common.cloneObject(administrator, AdministratorPo.class);
        int i = administratorPoMapper.updateByPrimaryKey(administratorPo);
        if (i > 0) {
            // 这里需要删除原来用户名的
            redisUtils.deleteKey(RedisPrefix.ADMIN + origin.getName());
            redisUtils.deleteKey(RedisPrefix.ADMIN + administrator.getId());
            if (!origin.getName().equals(administrator.getName()) && administrator.getName() != null) {
                redisUtils.deleteKey(RedisPrefix.ADMIN_NAME_EXIST + origin.getName());
            } else if (administrator.getValid() == 0) {
                redisUtils.deleteKey(RedisPrefix.ADMIN_NAME_EXIST + origin.getName());
            }
        }
        return i;
    }

    public Page<Administrator> selectAll(Integer page, Integer pageSize, String name, Integer valid, LocalDateTime beginTime, LocalDateTime endTime) {
        AdministratorPoExample example = new AdministratorPoExample();
        AdministratorPoExample.Criteria criteria = example.createCriteria();
        if (name != null) {
            criteria.andNameEqualTo(name);
        }
        if (valid != null) {
            criteria.andValidEqualTo(valid);
        }
        if (beginTime != null) {
            criteria.andGmtCreateGreaterThanOrEqualTo(beginTime);
        }
        if (endTime != null) {
            criteria.andGmtCreateLessThanOrEqualTo(endTime);
        }
        PageHelper.startPage(page, pageSize);
        List<AdministratorPo> administratorPos = administratorPoMapper.selectByExample(example);
        PageInfo<AdministratorPo> administratorPoPageInfo = new PageInfo<>(administratorPos);
        ArrayList<Administrator> administrators = new ArrayList<>();
        for (AdministratorPo administratorPo : administratorPos) {
            Administrator administrator = Common.cloneObject(administratorPo, Administrator.class);
            administrators.add(administrator);
        }
        return new Page<>(administrators, administratorPoPageInfo);
    }
}
