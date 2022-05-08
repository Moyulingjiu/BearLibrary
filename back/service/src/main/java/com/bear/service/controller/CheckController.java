package com.bear.service.controller;

import com.bear.login.*;
import com.bear.service.model.vo.receive.CheckVo;
import com.bear.service.service.CheckService;
import com.bear.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 打卡controller层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@RestController
@RequestMapping(value = "/check", produces = "application/json;charset=UTF-8")
public class CheckController {
    private final CheckService checkService;

    @Autowired
    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @PutMapping("/check")
    @UserLoginCheck
    public Object create(
            @Valid @RequestBody CheckVo checkVo,
            @LoginId Long userId,
            @LoginName String userName
    ) {
        return checkService.create(checkVo, userId, userName);
    }

    @GetMapping("/check/{id}/self")
    @UserLoginCheck
    public Object getSelf(
            @PathVariable Long id,
            @LoginId Long userId
    ) {
        return checkService.getSelf(id, userId);
    }

    @GetMapping("/check/{id}")
    @AdminLoginCheck
    public Object get(
            @PathVariable Long id
    ) {
        return checkService.get(id);
    }

    @GetMapping("/checks/self")
    @UserLoginCheck
    public Object getSelfAll(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) Long adminId,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String userComment,
            @RequestParam(required = false) String adminComment,
            @RequestParam(required = false) Long minPoint,
            @RequestParam(required = false) Long maxPoint,
            @RequestParam(required = false) Long minExp,
            @RequestParam(required = false) Long maxExp,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime beginTime,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime endTime,
            @LoginId Long userId
    ) {
        return checkService.getAll(page, pageSize, adminId, type, userComment, adminComment, minPoint, maxPoint, minExp, maxExp, beginTime, endTime, userId);
    }

    @GetMapping("/checks")
    @AdminLoginCheck
    public Object getAll(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long adminId,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String userComment,
            @RequestParam(required = false) String adminComment,
            @RequestParam(required = false) Long minPoint,
            @RequestParam(required = false) Long maxPoint,
            @RequestParam(required = false) Long minExp,
            @RequestParam(required = false) Long maxExp,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime beginTime,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime endTime
    ) {
        return checkService.getAll(page, pageSize, adminId, type, userComment, adminComment, minPoint, maxPoint, minExp, maxExp, beginTime, endTime, userId);
    }
}
