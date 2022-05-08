package com.bear.service.service;

import com.bear.model.Page;
import com.bear.model.SimplePerson;
import com.bear.service.dao.CheckDao;
import com.bear.service.model.bo.Check;
import com.bear.service.model.bo.CheckType;
import com.bear.service.model.vo.receive.CheckVo;
import com.bear.service.model.vo.ret.CheckRetVo;
import com.bear.util.Common;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 创建打卡
     *
     * @param checkVo  打卡信息
     * @param userId   用户id
     * @param userName 用户名
     * @return 打卡信息
     */
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
    public Object getAll(Integer page, Integer pageSize, Long adminId, Integer type, String userComment, String adminComment, Long minPoint, Long maxPoint, Long minExp, Long maxExp, LocalDateTime beginTime, LocalDateTime endTime, Long userId) {
        Page<Check> checkPage = checkDao.selectAll(page, pageSize, userId, adminId, type, userComment, adminComment, minPoint, maxPoint, minExp, maxExp, beginTime, endTime);
        ArrayList<CheckRetVo> checkRetVos = new ArrayList<>();
        for (Check check : checkPage.getList()) {
            CheckRetVo checkRetVo = Common.cloneObject(check, CheckRetVo.class);
            checkRetVos.add(checkRetVo);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.OK, new Page<>(checkRetVos, checkPage));
    }
}
