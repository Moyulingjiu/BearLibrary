package com.bear.service.controller;

import com.bear.login.AdminLoginCheck;
import com.bear.login.LoginId;
import com.bear.login.LoginName;
import com.bear.service.model.vo.receive.AdminCreateVo;
import com.bear.service.model.vo.receive.AdminLoginVo;
import com.bear.service.model.vo.receive.PasswordChangeVo;
import com.bear.service.service.AdministratorService;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 管理员controller层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@RestController
@RequestMapping(value = "/administrator", produces = "application/json;charset=UTF-8")
public class AdministratorController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping("/login")
    public Object login(
            @Valid @RequestBody AdminLoginVo adminLoginVo
    ) {
        return administratorService.login(adminLoginVo);
    }

    @PutMapping("/administrator")
    @AdminLoginCheck
    public Object create(
        @Valid @RequestBody AdminCreateVo adminCreateVo,
        @LoginId Long adminId,
        @LoginName String adminName
    ) {
        if (adminId != 1L) {
            return ResponseUtil.decorateReturnObject(ReturnNo.FORBIDDEN);
        }
        return administratorService.create(adminCreateVo, adminId, adminName);
    }

    @PostMapping("/administrator/password")
    @AdminLoginCheck
    public Object changePassword(
            @Valid @RequestBody PasswordChangeVo passwordChangeVo,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        return administratorService.changePassword(passwordChangeVo, adminId, adminName);
    }
}
