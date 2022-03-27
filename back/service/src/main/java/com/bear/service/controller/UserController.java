package com.bear.service.controller;

import com.bear.service.model.vo.receive.UserRegisterVo;
import com.bear.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody UserRegisterVo registerVo
    ) {
        return userService.register();
    }
}
