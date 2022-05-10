package com.bear.service.service;

import com.bear.model.Page;
import com.bear.model.SimplePerson;
import com.bear.service.dao.ContributionDao;
import com.bear.service.dao.UserDao;
import com.bear.service.model.bo.Contribution;
import com.bear.service.model.bo.User;
import com.bear.service.model.vo.receive.ContributionCommentVo;
import com.bear.service.model.vo.receive.ContributionVo;
import com.bear.service.model.vo.ret.ContributionRetVo;
import com.bear.service.util.RedisUtils;
import com.bear.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.JobKOctets;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * 贡献的service
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Service
public class ContributionService {
    @Autowired
    private ContributionDao contributionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 添加贡献记录（贡献记录需要审核后才会发放积分，发放积分后不可以撤回）
     *
     * @param contributionVo 贡献记录
     * @param adminId        管理员id
     * @param adminName      管理员名称
     * @return 添加结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Object createContribution(ContributionVo contributionVo, Long adminId, String adminName) {
        Contribution contribution = Common.cloneObject(contributionVo, Contribution.class);
        contribution.setCreate(new SimplePerson(adminId, adminName));
        Common.modifyObject(contribution, adminId, adminName);
        long insert = contributionDao.insert(contribution);
        if (insert <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.CREATED, insert);
    }

    /**
     * 修改贡献记录
     *
     * @param contributionCommentVo 贡献记录
     * @param id                    贡献记录id
     * @param adminId               管理员id
     * @param adminName             管理员名称
     * @return 修改结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Object update(ContributionCommentVo contributionCommentVo, Long id, Long adminId, String adminName) {
        Contribution contribution = contributionDao.selectById(id);
        if (contribution == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        contribution.setComment(contributionCommentVo.getComment());
        Common.modifyObject(contribution, adminId, adminName);
        int update = contributionDao.update(contribution);
        if (update <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }

    /**
     * 获取贡献记录
     *
     * @param id 贡献记录id
     * @return 贡献记录
     */
    @Transactional(rollbackFor = Exception.class)
    public Object getContribution(Long id) {
        Contribution contribution = contributionDao.selectById(id);
        if (contribution == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, Common.cloneObject(contribution, ContributionRetVo.class));
    }

    public Object checkContribution(Long id, Long adminId, String adminName) {
        Contribution contribution = contributionDao.selectById(id);
        if (contribution == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        User user = userDao.selectById(contribution.getUserId());
        if (user == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        String lock = redisUtils.lock(RedisPrefix.USER_LOCK + user.getId(), DurationTimeUtil.MINUTE, DurationTimeUtil.FIVE_SECOND);
        if (lock == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.TIME_OUT);
        }
        if (contribution.getAdministratorId() != null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ALREADY_CHECKED_CONTRIBUTION);
        }
        contribution.setAdministratorId(contribution.getCreate().getId());
        Common.modifyObject(contribution, adminId, adminName);
        int update = contributionDao.update(contribution);
        if (update <= 0) {
            throw new RuntimeException("更新贡献记录失败");
        }
        user.setContributionPoint(user.getContributionPoint() + contribution.getPoint());
        Common.modifyObject(user, adminId, adminName);
        int updateUser = userDao.update(user);
        if (updateUser <= 0) {
            throw new RuntimeException("更新用户积分失败");
        }
        redisUtils.unlock(RedisPrefix.USER_LOCK + user.getId(), lock);
        return ResponseUtil.success();
    }

    /**
     * 条件分页查询贡献
     *
     * @param page                页码
     * @param pageSize            页面大小
     * @param userId              用户id
     * @param contributionAdminId 贡献管理员id
     * @param comment             贡献评论
     * @param minPoint            最小积分
     * @param maxPoint            最大积分
     * @param beginTime           开始时间
     * @param endTime             结束时间
     * @return 贡献记录
     */
    public Object selectAll(Integer page, Integer pageSize, Long userId, Long contributionAdminId, String comment, Long minPoint, Long maxPoint, LocalDateTime beginTime, LocalDateTime endTime) {
        Page<Contribution> contributionPage = contributionDao.selectAll(page, pageSize, userId, contributionAdminId, comment, minPoint, maxPoint, beginTime, endTime);
        ArrayList<ContributionRetVo> contributionRetVos = new ArrayList<>();
        for (Contribution contribution : contributionPage.getList()) {
            contributionRetVos.add(Common.cloneObject(contribution, ContributionRetVo.class));
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, new Page<>(contributionRetVos, contributionPage));
    }
}
