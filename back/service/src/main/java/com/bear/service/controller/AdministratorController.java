package com.bear.service.controller;

import com.bear.service.model.vo.receive.AdminLoginVo;
import com.bear.service.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    AdministratorService administratorService;

    @PostMapping("/login")
    public Object login(
            @Valid @RequestBody AdminLoginVo adminLoginVo
    ) {
        return administratorService.login(adminLoginVo);
    }
}
