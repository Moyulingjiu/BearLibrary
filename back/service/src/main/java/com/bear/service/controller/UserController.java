package com.bear.service.controller;

import com.bear.login.AdminLoginCheck;
import com.bear.login.LoginId;
import com.bear.login.LoginName;
import com.bear.login.UserLoginCheck;
import com.bear.service.model.vo.receive.*;
import com.bear.service.service.UserService;
import com.bear.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @PostMapping("/wxlogin")
    public Object wxLogin(
    ) {
        // todo: 微信登陆
        return "（接口暂未实现）";
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

    @PostMapping("/user/password")
    @UserLoginCheck
    public Object changePassword(
            @Valid @RequestBody PasswordChangeVo passwordChangeVo,
            @LoginId Long id,
            @LoginName String name
    ) {
        return userService.changePassword(passwordChangeVo, id, name);
    }

    @PostMapping("/user/{id}/password")
    @AdminLoginCheck
    public Object resetUserPassword(
            @PathVariable Long id,
            @Valid @RequestBody PasswordResetVo passwordResetVo,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        return userService.resetPassword(id, passwordResetVo, adminId, adminName);
    }

    @GetMapping("/administrator/user/{id}")
    @AdminLoginCheck
    public Object getByAdmin(
            @NotNull @PathVariable Long id
    ) {
        return userService.getByAdmin(id);
    }

    @GetMapping("/administrator/users")
    @AdminLoginCheck
    public Object getAllByAdmin(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer valid,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String nickname,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime beginTime,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime endTime,
            @RequestParam(required = false) Long minWalk,
            @RequestParam(required = false) Long maxWalk,
            @RequestParam(required = false) Long minRead,
            @RequestParam(required = false) Long maxRead,
            @RequestParam(required = false) Long minSport,
            @RequestParam(required = false) Long maxSport,
            @RequestParam(required = false) Long minArt,
            @RequestParam(required = false) Long maxArt,
            @RequestParam(required = false) Long minPractice,
            @RequestParam(required = false) Long maxPractice
    ) {
        return userService.getAllByAdmin(page, pageSize, name, valid, gender, phone, nickname, beginTime, endTime, minWalk, maxWalk, minRead, maxRead, minSport, maxSport, minArt, maxArt, minPractice, maxPractice);
    }

    @PostMapping("/user/self")
    @UserLoginCheck
    public Object changeSelfUser(
            @Valid @RequestBody UserVo userVo,
            @LoginId Long userId,
            @LoginName String userName
    ) {
        return userService.changeUser(userVo, userId, userName);
    }

    @PostMapping("/user/{id}")
    @AdminLoginCheck
    public Object changeUser(
            @PathVariable Long id,
            @Valid @RequestBody UserAdminVo userAdminVo,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        return userService.changeOtherUser(id, userAdminVo, adminId, adminName);
    }

    @DeleteMapping("/user/{id}")
    @AdminLoginCheck
    public Object deleteUser(
            @PathVariable Long id,
            @LoginId Long userId,
            @LoginName String userName
    ) {
        return userService.delete(id, userId, userName);
    }
}
