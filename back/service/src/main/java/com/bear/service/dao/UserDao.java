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
        Object obj = redisUtils.getObj(RedisPrefix.USER + id);
        if (obj != null) {
            return (User) obj;
        }
        UserPo userPo = userPoMapper.selectByPrimaryKey(id);
        User user = Common.cloneObject(userPo, User.class);
        redisUtils.putObj(RedisPrefix.USER + id, user);
        return user;
    }

    public boolean existName(String name) {
        String s = redisUtils.get(RedisPrefix.USER_NAME_EXIST + name);
        if (StringUtils.isNotEmpty(s)) {
            return true;
        }
        return selectByName(name) != null;
    }

    public User selectByName(String name) {
        Object obj = redisUtils.getObj(RedisPrefix.USER + name);
        if (obj != null) {
            return (User) obj;
        }
        UserPoExample example = new UserPoExample();
        UserPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andValidEqualTo(1);
        List<UserPo> userPos = userPoMapper.selectByExample(example);
        if (userPos.size() == 0) {
            redisUtils.putObj(RedisPrefix.USER + name, null);
            return null;
        }
        // 写入redis
        redisUtils.put(RedisPrefix.USER_NAME_EXIST + name, "1");
        User user = Common.cloneObject(userPos.get(0), User.class);
        redisUtils.putObj(RedisPrefix.USER + name, user);
        return user;
    }

    public int update(User user) {
        User origin = selectById(user.getId());
        UserPo userPo = Common.cloneObject(user, UserPo.class);
        int i = userPoMapper.updateByPrimaryKey(userPo);
        if (i > 0) {
            // 这里需要删除原来的用户名对应的缓存，可能用户名已经修改
            redisUtils.deleteKey(RedisPrefix.USER + origin.getName());
            redisUtils.deleteKey(RedisPrefix.USER + user.getId());
            if (!origin.getName().equals(user.getName())) {
                redisUtils.deleteKey(RedisPrefix.USER_NAME_EXIST + origin.getName());
            }
        }
        return i;
    }

    public int insert(User user) {
        UserPo userPo = Common.cloneObject(user, UserPo.class);
        int insert = userPoMapper.insert(userPo);
        if (insert > 0) {
            // 插入的时候要把null给删了，不然查缓存还是null。
            redisUtils.deleteKey(RedisPrefix.USER + user.getName());
        }
        return insert;
    }
}
