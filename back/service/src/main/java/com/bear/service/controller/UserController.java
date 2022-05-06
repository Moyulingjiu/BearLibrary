package com.bear.service.controller;

import com.bear.login.AdminLoginCheck;
import com.bear.login.LoginId;
import com.bear.login.LoginName;
import com.bear.login.UserLoginCheck;
import com.bear.service.model.vo.receive.UserLoginVo;
import com.bear.service.model.vo.receive.UserRegisterVo;
import com.bear.service.service.UserService;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 用户controller层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Object register(
            @Valid @RequestBody UserRegisterVo registerVo
    ) {
        return userService.register(registerVo);
    }

    @PostMapping("/login")
    public Object login(
            @Valid @RequestBody UserLoginVo userLoginVo
    ) {
        return userService.login(userLoginVo);
    }

    @GetMapping("/user/self")
    @UserLoginCheck
    public Object getSelf(
            @LoginId Long userId
    ) {
        return userService.get(userId);
    }

    @GetMapping("/user/{id}")
    @UserLoginCheck
    public Object get(
            @NotNull @PathVariable Long id,
            @LoginId Long userId
    ) {
        if (id.equals(userId)) {
            return userService.get(id);
        }
        return userService.getOther(id);
    }
}
