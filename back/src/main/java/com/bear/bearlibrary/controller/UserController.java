package com.bear.bearlibrary.controller;

import com.bear.bearlibrary.model.vo.Authentication;
import com.bear.bearlibrary.utils.CommonUtils;
import com.bear.bearlibrary.utils.ReturnObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller
 *
 * @author 墨羽翎玖
 */
@Api(tags = "登陆模拟")
@RestController
@CrossOrigin
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public Object login() {
        return CommonUtils.decorateReturnObject(new ReturnObject(new Authentication("token")));
    }
}
