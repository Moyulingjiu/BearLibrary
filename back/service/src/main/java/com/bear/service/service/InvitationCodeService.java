package com.bear.service.service;

import com.bear.model.SimplePerson;
import com.bear.service.dao.InvitationCodeDao;
import com.bear.service.model.bo.InvitationCode;
import com.bear.service.model.vo.receive.InvitationCodeVo;
import com.bear.service.util.StringUtils;
import com.bear.util.Common;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 邀请码service层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@Service
public class InvitationCodeService {
    @Autowired
    InvitationCodeDao invitationCodeDao;

    /**
     * 创建邀请码
     *
     * @param invitationCodeVo 邀请码vo
     * @param userId 用户id
     * @param userName 用户名
     * @return 邀请码
     */
    @Transactional(rollbackFor = Exception.class)
    public Object create(InvitationCodeVo invitationCodeVo, Long userId, String userName) {
        InvitationCode invitationCode = Common.cloneObject(invitationCodeVo, InvitationCode.class);
        Common.modifyObject(invitationCode, userId, userName);
        invitationCode.setCreate(new SimplePerson(userId, userName));
        invitationCode.setAdministratorId(userId);
        if (!StringUtils.validInvitationCode(invitationCode.getCode())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_INVITATION_CODE);
        }
        if (invitationCode.getValidTime().isBefore(LocalDateTime.now())) {
            return ResponseUtil.decorateReturnObject(ReturnNo.ILLEGAL_INVITATION_CODE_VALID_TIME);
        }
        InvitationCode origin = invitationCodeDao.selectByCode(invitationCode.getCode());
        if (origin != null) {
            return ResponseUtil.decorateReturnObject(ReturnNo.SAME_INVITATION_CODE);
        }
        long insert = invitationCodeDao.insert(invitationCode);
        if (insert <= 0) {
            return ResponseUtil.decorateReturnObject(ReturnNo.INTERNAL_SERVER_ERR);
        }
        return ResponseUtil.decorateReturnObject(ReturnNo.CREATED, insert);
    }
}
