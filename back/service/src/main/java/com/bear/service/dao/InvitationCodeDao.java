package com.bear.service.dao;

import com.bear.service.mapper.InvitationCodePoMapper;
import com.bear.service.model.bo.InvitationCode;
import com.bear.service.model.po.InvitationCodePo;
import com.bear.service.model.po.InvitationCodePoExample;
import com.bear.util.Common;
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

    public InvitationCode selectById(Long id) {
        InvitationCodePo invitationCodePo = invitationCodePoMapper.selectByPrimaryKey(id);
        return Common.cloneObject(invitationCodePo, InvitationCode.class);
    }

    public InvitationCode selectByCode(String code) {
        InvitationCodePoExample example = new InvitationCodePoExample();
        InvitationCodePoExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        // 有效期时间还没有过
        criteria.andValidTimeGreaterThan(LocalDateTime.now());
        List<InvitationCodePo> invitationCodePos = invitationCodePoMapper.selectByExample(example);
        if (invitationCodePos.size() == 0) {
            return null;
        }
        return Common.cloneObject(invitationCodePos.get(0), InvitationCode.class);
    }

    public int updateById(InvitationCode invitationCode) {
        InvitationCodePo invitationCodePo = Common.cloneObject(invitationCode, InvitationCodePo.class);
        return invitationCodePoMapper.updateByPrimaryKey(invitationCodePo);
    }
}
