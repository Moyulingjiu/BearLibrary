package com.bear.service.dao;

import com.bear.model.Page;
import com.bear.service.mapper.ContributionPoMapper;
import com.bear.service.model.bo.Contribution;
import com.bear.service.model.po.ContributionPo;
import com.bear.service.model.po.ContributionPoExample;
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
 * 贡献dao层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Repository
public class ContributionDao {
    @Autowired
    private ContributionPoMapper contributionPoMapper;
    @Autowired
    private RedisUtils redisUtils;

    public Contribution selectById(Long id) {
        Object obj = redisUtils.getObj(RedisPrefix.CONTRIBUTION + id);
        if (obj != null) {
            return (Contribution) obj;
        }
        ContributionPo contributionPo = contributionPoMapper.selectByPrimaryKey(id);
        Contribution contribution = Common.cloneObject(contributionPo, Contribution.class);
        redisUtils.putObj(RedisPrefix.CONTRIBUTION + id, contribution);
        return contribution;
    }

    public int update(Contribution contribution) {
        ContributionPo contributionPo = Common.cloneObject(contribution, ContributionPo.class);
        int i = contributionPoMapper.updateByPrimaryKey(contributionPo);
        redisUtils.deleteKey(RedisPrefix.CONTRIBUTION + contribution.getId());
        return i;
    }

    public long insert(Contribution contribution) {
        ContributionPo contributionPo = Common.cloneObject(contribution, ContributionPo.class);
        int insert = contributionPoMapper.insert(contributionPo);
        if (insert == 0 || contributionPo.getId() == null) {
            return 0L;
        }
        redisUtils.deleteKey(RedisPrefix.CONTRIBUTION + contributionPo.getId());
        return contributionPo.getId();
    }

    public int delete(Long id) {
        int i = contributionPoMapper.deleteByPrimaryKey(id);
        redisUtils.deleteKey(RedisPrefix.CONTRIBUTION + id);
        return i;
    }

    public Page<Contribution> selectAll(Integer page, Integer pageSize, Long userId, Long adminId, String comment, Long minPoint, Long maxPoint, LocalDateTime beginTime, LocalDateTime endTime) {
        ContributionPoExample example = new ContributionPoExample();
        ContributionPoExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (adminId != null) {
            criteria.andAdministratorIdEqualTo(adminId);
        }
        if (comment != null) {
            criteria.andCommentLike("%" + comment + "%");
        }
        if (minPoint != null) {
            criteria.andPointGreaterThanOrEqualTo(minPoint);
        }
        if (maxPoint != null) {
            criteria.andPointLessThanOrEqualTo(maxPoint);
        }
        if (beginTime != null) {
            criteria.andGmtCreateGreaterThanOrEqualTo(beginTime);
        }
        if (endTime != null) {
            criteria.andGmtCreateLessThanOrEqualTo(endTime);
        }
        PageHelper.startPage(page, pageSize);
        List<ContributionPo> contributionPos = contributionPoMapper.selectByExample(example);
        PageInfo<ContributionPo> contributionPoPageInfo = new PageInfo<>(contributionPos);
        ArrayList<Contribution> contributions = new ArrayList<>();
        for (ContributionPo contributionPo : contributionPos) {
            Contribution contribution = Common.cloneObject(contributionPo, Contribution.class);
            contributions.add(contribution);
        }
        return new Page<>(contributions, contributionPoPageInfo);
    }
}
