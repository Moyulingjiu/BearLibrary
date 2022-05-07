package com.bear.service.controller;

import com.bear.login.LoginId;
import com.bear.login.LoginName;
import com.bear.login.UserLoginCheck;
import com.bear.service.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片controller层
 *
 * @author moyulingjiu
 * create 2022年5月7日
 */
@RestController
@RequestMapping(value = "/picture", produces = "application/json;charset=UTF-8")
public class PictureController {
    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping("/upload")
    @UserLoginCheck
    public Object upload(
            MultipartFile fileUpload,
            @LoginId Long userId,
            @LoginName String userName
    ) {
        return "";
    }

    @PostMapping("/download")
    @UserLoginCheck
    public Object download(
    ) {
        return "";
    }
}
