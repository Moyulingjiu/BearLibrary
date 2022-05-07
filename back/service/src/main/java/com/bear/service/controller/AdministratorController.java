package com.bear.service.controller;

import com.bear.login.AdminLoginCheck;
import com.bear.login.LoginId;
import com.bear.login.LoginName;
import com.bear.service.model.vo.receive.AdminCreateVo;
import com.bear.service.model.vo.receive.AdminLoginVo;
import com.bear.service.model.vo.receive.NameChangeVo;
import com.bear.service.model.vo.receive.PasswordChangeVo;
import com.bear.service.service.AdministratorService;
import com.bear.util.Common;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

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

    @PostMapping("/administrator/name")
    @AdminLoginCheck
    public Object changeName(
            @Valid @RequestBody NameChangeVo name,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        return administratorService.changeName(name, adminId, adminName);
    }

    @DeleteMapping("/administrator/{id}")
    @AdminLoginCheck
    public Object delete(
            @PathVariable Long id,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        if (adminId != 1L) {
            return ResponseUtil.decorateReturnObject(ReturnNo.FORBIDDEN);
        }
        return administratorService.delete(id, adminId, adminName);
    }

    @GetMapping("/administrator/{id}")
    @AdminLoginCheck
    public Object get(
            @PathVariable Long id,
            @LoginId Long adminId
    ) {
        if (adminId != 1L) {
            return ResponseUtil.decorateReturnObject(ReturnNo.FORBIDDEN);
        }
        return administratorService.get(id);
    }

    @GetMapping("/administrators")
    @AdminLoginCheck
    public Object getAll(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer valid,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime beginTime,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime endTime,
            @LoginId Long adminId
    ) {
        if (adminId != 1L) {
            return ResponseUtil.decorateReturnObject(ReturnNo.FORBIDDEN);
        }
        return administratorService.getAll(page, pageSize, name, valid, beginTime, endTime);
    }
}
