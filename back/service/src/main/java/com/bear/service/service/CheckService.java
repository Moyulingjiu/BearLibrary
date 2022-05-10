package com.bear.service.service;

import com.bear.model.Page;
import com.bear.model.SimplePerson;
import com.bear.service.dao.CheckDao;
import com.bear.service.dao.UserDao;
import com.bear.service.model.bo.Check;
import com.bear.service.model.bo.CheckStatus;
import com.bear.service.model.bo.CheckType;
import com.bear.service.model.bo.User;
import com.bear.service.model.vo.receive.CheckAdminVo;
import com.bear.service.model.vo.receive.CheckVo;
import com.bear.service.model.vo.ret.CheckRetVo;
import com.bear.service.util.RedisUtils;
import com.bear.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * check service层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Service
public class CheckService {
    @Autowired
    private CheckDao checkDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 创建打卡
     *
     * @param checkVo  打卡信息
     * @param userId   用户id
     * @param userName 用户名
     * @return 打卡信息
     */
    @Transactional(rollbackFor = Exception.class)
    public Object create(CheckVo checkVo, Long userId, String userName) {
        Check check = new Check();
        check.setUserId(userId);
        check.setUrl(checkVo.getUrl());
        check.setUserComment(checkVo.getComment());
        Integer type = checkVo.getType();
        if (type >= 0 && type < CheckType.values().length) {
            check.setType(CheckType.values()[type]);
        } else {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_REQUEST);
        }
        check.setCreate(new SimplePerson(userId, userName));
        Common.modifyObject(check, userId, userName);
        System.out.println(check);
        int insert = checkDao.insert(check);
        if (insert <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.CREATED);
    }

    /**
     * 获取打卡
     *
     * @param id 打卡id
     * @return 打卡信息
     */
    @Transactional(rollbackFor = Exception.class)
    public Object get(Long id) {
        Check check = checkDao.selectById(id);
        if (check == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, Common.cloneObject(check, CheckRetVo.class));
    }

    /**
     * 获取自己的打卡
     *
     * @param id 打卡id
     * @return 打卡信息
     */
    @Transactional(rollbackFor = Exception.class)
    public Object getSelf(Long id, Long userId) {
        Check check = checkDao.selectById(id);
        if (check == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        if (!check.getUserId().equals(userId)) {
            return ResponseUtil.decorateReturnObject(ReturnNo.FORBIDDEN);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, Common.cloneObject(check, CheckRetVo.class));
    }

    /**
     * 获取自己的打卡
     *
     * @param page         页码
     * @param pageSize     页大小
     * @param adminId      管理员id
     * @param type         打卡类型
     * @param userComment  用户备注
     * @param adminComment 管理员备注
     * @param minPoint     最小积分
     * @param maxPoint     最大积分
     * @param minExp       最小经验
     * @param maxExp       最大经验
     * @param beginTime    开始时间
     * @param endTime      结束时间
     * @param userId       用户id
     * @return 打卡信息
     */
    @Transactional(rollbackFor = Exception.class)
    public Object getAll(Integer page, Integer pageSize, Long adminId, Byte status, Integer type, String userComment, String adminComment, Long minPoint, Long maxPoint, Long minExp, Long maxExp, LocalDateTime beginTime, LocalDateTime endTime, Long userId) {
        Page<Check> checkPage = checkDao.selectAll(page, pageSize, userId, adminId, status, type, userComment, adminComment, minPoint, maxPoint, minExp, maxExp, beginTime, endTime);
        ArrayList<CheckRetVo> checkRetVos = new ArrayList<>();
        for (Check check : checkPage.getList()) {
            CheckRetVo checkRetVo = Common.cloneObject(check, CheckRetVo.class);
            checkRetVos.add(checkRetVo);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, new Page<>(checkRetVos, checkPage));
    }

    /**
     * 管理员审核打卡
     *
     * @param id           打卡id
     * @param checkAdminVo 打卡审核信息
     * @param adminId      管理员id
     * @param adminName    管理员名称
     * @return 打卡审核信息
     */
    @Transactional(rollbackFor = Exception.class)
    public Object check(Long id, CheckAdminVo checkAdminVo, Long adminId, String adminName) {
        Check check = checkDao.selectById(id);
        if (check == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        if (check.getStatus() != CheckStatus.WAITING) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ALREADY_CHECKED_PUNCH);
        }
        User user = userDao.selectById(check.getUserId());
        if (user == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.RESOURCE_NOT_EXIST);
        }
        String lock = redisUtils.lock(RedisPrefix.USER_LOCK + user.getId(), DurationTimeUtil.MINUTE, DurationTimeUtil.FIVE_SECOND);
        if (lock == null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.TIME_OUT);
        }
        Common.modifyObject(check, adminId, adminName);
        check.setAdminComment(checkAdminVo.getComment());
        check.setExp(checkAdminVo.getExp());
        check.setPoint(checkAdminVo.getPoint());
        if (checkAdminVo.getPass()) {
            check.setStatus(CheckStatus.PASS);
        } else {
            check.setStatus(CheckStatus.REJECT);
        }
        check.setAdministratorId(adminId);
        int update = checkDao.update(check);
        if (update > 0) {
            if (check.getStatus() == CheckStatus.PASS) {
                switch (check.getType()) {
                    case WALK:
                        user.setHonorPoint(user.getHonorPoint() + check.getPoint());
                        user.setWalk(user.getWalk() + check.getExp());
                        break;
                    case READ:
                        user.setHonorPoint(user.getHonorPoint() + check.getPoint());
                        user.setRead(user.getRead() + check.getExp());
                        break;
                    case SPORT:
                        user.setHonorPoint(user.getHonorPoint() + check.getPoint());
                        user.setSport(user.getSport() + check.getExp());
                        break;
                    case ART:
                        user.setHonorPoint(user.getHonorPoint() + check.getPoint());
                        user.setArt(user.getArt() + check.getExp());
                        break;
                    case PRACTICE:
                        user.setHonorPoint(user.getHonorPoint() + check.getPoint());
                        user.setPractice(user.getPractice() + check.getExp());
                        break;
                    case STUDY_ADVANCE:
                        user.setSelfControlPoint(user.getSelfControlPoint() + check.getPoint());
                        break;
                    default:
                }
                int i = userDao.update(user);
                if (i <= 0) {
                    throw new RuntimeException("无法更新经验值与积分");
                }
                redisUtils.unlock(RedisPrefix.USER_LOCK + user.getId(), lock);
            }
        } else {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.success();
    }
}
