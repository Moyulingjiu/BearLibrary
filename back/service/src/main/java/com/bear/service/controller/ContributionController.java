package com.bear.service.controller;

import com.bear.login.AdminLoginCheck;
import com.bear.login.LoginId;
import com.bear.login.LoginName;
import com.bear.login.UserLoginCheck;
import com.bear.service.model.vo.receive.ContributionCommentVo;
import com.bear.service.model.vo.receive.ContributionVo;
import com.bear.service.service.ContributionService;
import com.bear.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 贡献controller层
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
@RestController
@RequestMapping(value = "/contribution", produces = "application/json;charset=UTF-8")
public class ContributionController {
    private final ContributionService contributionService;

    @Autowired
    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PutMapping("/contribution")
    @AdminLoginCheck
    public Object createContribution(
            @Valid @RequestBody ContributionVo contributionVo,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        return contributionService.createContribution(contributionVo, adminId, adminName);
    }

    @PostMapping("/contribution/{id}")
    @AdminLoginCheck
    public Object updateContribution(
            @Valid @RequestBody ContributionCommentVo contributionCommentVo,
            @PathVariable Long id,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        return contributionService.update(contributionCommentVo, id, adminId, adminName);
    }

    @GetMapping("/contribution/{id}")
    @AdminLoginCheck
    public Object getContribution(
            @PathVariable Long id
    ) {
        return contributionService.getContribution(id);
    }

    @PostMapping("/contribution/{id}/check")
    @AdminLoginCheck
    public Object checkContribution(
            @PathVariable Long id,
            @LoginId Long adminId,
            @LoginName String adminName
    ) {
        return contributionService.checkContribution(id, adminId, adminName);
    }

    @GetMapping("/contributions")
    @AdminLoginCheck
    public Object selectAll(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contributionAdminId,
            @RequestParam(required = false) String comment,
            @RequestParam(required = false) Long minPoint,
            @RequestParam(required = false) Long maxPoint,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime beginTime,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime endTime
    ) {
        return contributionService.selectAll(page, pageSize, userId, contributionAdminId, comment, minPoint, maxPoint, beginTime, endTime);
    }

    @GetMapping("/contributions/self")
    @UserLoginCheck
    public Object selectSelfAll(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) Long contributionAdminId,
            @RequestParam(required = false) String comment,
            @RequestParam(required = false) Long minPoint,
            @RequestParam(required = false) Long maxPoint,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime beginTime,
            @DateTimeFormat(pattern = Common.DATE_TIME_FORMAT) @RequestParam(required = false) LocalDateTime endTime,
            @LoginId Long userId
    ) {
        return contributionService.selectAll(page, pageSize, userId, contributionAdminId, comment, minPoint, maxPoint, beginTime, endTime);
    }
}
