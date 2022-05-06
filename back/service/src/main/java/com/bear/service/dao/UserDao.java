package com.bear.service.dao;

import com.bear.service.mapper.UserPoMapper;
import com.bear.service.model.bo.User;
import com.bear.service.model.po.UserPo;
import com.bear.service.model.po.UserPoExample;
import com.bear.service.util.RedisUtils;
import com.bear.service.util.StringUtils;
import com.bear.util.Common;
import com.bear.util.RedisPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Repository
public class UserDao {
    @Autowired
    private UserPoMapper userPoMapper;
    @Autowired
    private RedisUtils redisUtils;

    public User selectById(Long id) {
        UserPo userPo = userPoMapper.selectByPrimaryKey(id);
        return Common.cloneObject(userPo, User.class);
    }

    public boolean existName(String name) {
        String s = redisUtils.get(RedisPrefix.USER_NAME_EXIST + name);
        if (StringUtils.isNotEmpty(s)) {
            return true;
        }
        return selectByName(name) != null;
    }

    public User selectByName(String name) {
        UserPoExample example = new UserPoExample();
        UserPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andValidEqualTo(1);
        List<UserPo> userPos = userPoMapper.selectByExample(example);
        if (userPos.size() == 0) {
            return null;
        }
        // 写入redis
        redisUtils.put(RedisPrefix.USER_NAME_EXIST + name, "1");
        return Common.cloneObject(userPos.get(0), User.class);
    }

    public int updateById(User user) {
        UserPo userPo = Common.cloneObject(user, UserPo.class);
        return userPoMapper.updateByPrimaryKey(userPo);
    }

    public int insert(User user) {
        UserPo userPo = Common.cloneObject(user, UserPo.class);
        return userPoMapper.insert(userPo);
    }
}
