package com.bear.service.dao;

import com.bear.model.Page;
import com.bear.service.mapper.CheckPoMapper;
import com.bear.service.model.bo.Check;
import com.bear.service.model.po.CheckPo;
import com.bear.service.model.po.CheckPoExample;
import com.bear.service.util.RedisUtils;
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
 * 打卡dao层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Repository
public class CheckDao {
    @Autowired
    private CheckPoMapper checkPoMapper;
    @Autowired
    private RedisUtils redisUtils;

    public Check selectById(Long id) {
        Object obj = redisUtils.getObj(RedisPrefix.CHECK + id);
        if (obj != null) {
            return (Check) obj;
        }
        CheckPo checkPo = checkPoMapper.selectByPrimaryKey(id);
        Check check = Common.cloneObject(checkPo, Check.class);
        redisUtils.putObj(RedisPrefix.CHECK + id, check);
        return check;
    }

    public int update(Check check) {
        CheckPo checkPo = Common.cloneObject(check, CheckPo.class);
        int i = checkPoMapper.updateByPrimaryKey(checkPo);
        redisUtils.deleteKey(RedisPrefix.CHECK + check.getId());
        return i;
    }

    public long insert(Check check) {
        CheckPo checkPo = Common.cloneObject(check, CheckPo.class);
        int insert = checkPoMapper.insert(checkPo);
        if (insert == 0 || checkPo.getId() == null) {
            return 0L;
        }
        redisUtils.deleteKey(RedisPrefix.CHECK + checkPo.getId());
        return checkPo.getId();
    }

    public Page<Check> selectAll(Integer page, Integer pageSize, Long userId, Long adminId, Byte status, Integer type, String userComment, String adminComment, Long minPoint, Long maxPoint, Long minExp, Long maxExp, LocalDateTime beginTime, LocalDateTime endTime) {
        CheckPoExample example = new CheckPoExample();
        CheckPoExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (adminId != null) {
            criteria.andAdministratorIdEqualTo(adminId);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        if (userComment != null) {
            criteria.andUserCommentLike("%" + userComment + "%");
        }
        if (adminComment != null) {
            criteria.andAdminCommentLike("%" + adminComment + "%");
        }
        if (minPoint != null) {
            criteria.andPointGreaterThanOrEqualTo(minPoint);
        }
        if (maxPoint != null) {
            criteria.andPointLessThanOrEqualTo(maxPoint);
        }
        if (minExp != null) {
            criteria.andExpGreaterThanOrEqualTo(minExp);
        }
        if (maxExp != null) {
            criteria.andExpLessThanOrEqualTo(maxExp);
        }
        if (beginTime != null) {
            criteria.andGmtCreateGreaterThanOrEqualTo(beginTime);
        }
        if (endTime != null) {
            criteria.andGmtCreateLessThanOrEqualTo(endTime);
        }
        PageHelper.startPage(page, pageSize);
        List<CheckPo> checkPos = checkPoMapper.selectByExample(example);
        PageInfo<CheckPo> checkPoPageInfo = new PageInfo<>(checkPos);
        ArrayList<Check> checks = new ArrayList<>();
        for (CheckPo checkPo : checkPos) {
            Check check = Common.cloneObject(checkPo, Check.class);
            checks.add(check);
        }
        return new Page<>(checks, checkPoPageInfo);
    }
}
