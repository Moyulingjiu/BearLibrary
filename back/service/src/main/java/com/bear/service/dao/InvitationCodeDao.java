package com.bear.service.dao;

import com.bear.service.mapper.InvitationCodePoMapper;
import com.bear.service.model.bo.InvitationCode;
import com.bear.service.model.bo.User;
import com.bear.service.model.po.InvitationCodePo;
import com.bear.service.model.po.InvitationCodePoExample;
import com.bear.service.util.RedisUtils;
import com.bear.util.Common;
import com.bear.util.RedisPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 邀请码dao层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@Repository
public class InvitationCodeDao {
    @Autowired
    private InvitationCodePoMapper invitationCodePoMapper;
    @Autowired
    private RedisUtils redisUtils;

    public InvitationCode selectById(Long id) {
        Object obj = redisUtils.getObj(RedisPrefix.INVITATION_CODE + id);
        if (obj != null) {
            return (InvitationCode) obj;
        }
        InvitationCodePo invitationCodePo = invitationCodePoMapper.selectByPrimaryKey(id);
        InvitationCode invitationCode = Common.cloneObject(invitationCodePo, InvitationCode.class);
        redisUtils.putObj(RedisPrefix.INVITATION_CODE + id, invitationCode);
        return invitationCode;
    }

    public InvitationCode selectByCode(String code) {
        Object obj = redisUtils.getObj(RedisPrefix.INVITATION_CODE + code);
        if (obj != null) {
            return (InvitationCode) obj;
        }
        InvitationCodePoExample example = new InvitationCodePoExample();
        InvitationCodePoExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        // 有效期时间还没有过
        criteria.andValidTimeGreaterThan(LocalDateTime.now());
        List<InvitationCodePo> invitationCodePos = invitationCodePoMapper.selectByExample(example);
        if (invitationCodePos.size() == 0) {
            // 对于查不到的也应该放入redis。
            redisUtils.putObj(RedisPrefix.INVITATION_CODE + code, null);
            return null;
        }
        InvitationCode invitationCode = Common.cloneObject(invitationCodePos.get(0), InvitationCode.class);
        redisUtils.putObj(RedisPrefix.INVITATION_CODE + code, invitationCode);
        return invitationCode;
    }

    public int updateById(InvitationCode invitationCode) {
        InvitationCode origin = selectById(invitationCode.getId());
        InvitationCodePo invitationCodePo = Common.cloneObject(invitationCode, InvitationCodePo.class);
        int i = invitationCodePoMapper.updateByPrimaryKey(invitationCodePo);
        if (i > 0) {
            redisUtils.deleteKey(RedisPrefix.INVITATION_CODE + invitationCode.getId());
            redisUtils.deleteKey(RedisPrefix.INVITATION_CODE + origin.getCode());
        }
        return i;
    }
}
