package com.bear.service.controller;

import com.bear.login.AdminLoginCheck;
import com.bear.login.LoginId;
import com.bear.login.LoginName;
import com.bear.service.model.vo.receive.InvitationCodeVo;
import com.bear.service.service.AdministratorService;
import com.bear.service.service.InvitationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 邀请码controller层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@RestController
@RequestMapping(value = "/invitation_code", produces = "application/json;charset=UTF-8")
public class InvitationCodeController {

    private final InvitationCodeService invitationCodeService;

    @Autowired
    public InvitationCodeController(InvitationCodeService invitationCodeService) {
        this.invitationCodeService = invitationCodeService;
    }

    @PutMapping("/invitation_code")
    @AdminLoginCheck
    public Object create(
            @Valid @RequestBody InvitationCodeVo invitationCodeVo,
            @LoginId Long id,
            @LoginName String name
    ) {
        System.out.println(invitationCodeVo);
        return invitationCodeService.create(invitationCodeVo, id, name);
    }
}
