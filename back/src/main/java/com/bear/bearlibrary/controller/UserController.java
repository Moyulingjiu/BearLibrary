package com.bear.bearlibrary.controller;

import com.bear.bearlibrary.model.vo.AuthenticationVo;
import com.bear.bearlibrary.model.vo.UserLoginVo;
import com.bear.bearlibrary.model.vo.UserRegisterVo;
import com.bear.bearlibrary.model.vo.UserRetVo;
import com.bear.bearlibrary.utils.CommonUtils;
import com.bear.bearlibrary.utils.ReturnObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 用户controller
 *
 * @author 墨羽翎玖
 */
@Api(tags = "用户请求")
@RestController
@CrossOrigin
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    /**
     * 注册
     *
     * @param userRegisterVo 注册信息
     * @return token
     */
    @ApiOperation(value = "注册")
    @ApiParam()
    @PostMapping("/register")
    public Object register(
            @ApiParam(name = "userRegisterVo", value = "注册信息", required = true)
            @RequestBody UserRegisterVo userRegisterVo
    ) {
        return CommonUtils.decorateReturnObject(new ReturnObject(new AuthenticationVo("token")));
    }

    /**
     * 登陆
     *
     * @param userLoginVo 登陆信息
     * @return token
     */
    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public Object login(
            @ApiParam(name = "userRegisterVo", value = "登录信息", required = true)
            @RequestBody UserLoginVo userLoginVo
    ) {
        return CommonUtils.decorateReturnObject(new ReturnObject(new AuthenticationVo("token")));
    }

    /**
     * 查询自己
     *
     * @param authenticationVo token
     * @return 自己的信息
     */
    @ApiOperation(value = "查询自己")
    @GetMapping("/user")
    public Object selectSelf(
            @ApiParam(name = "authenticationVo", value = "token", required = true)
            @RequestParam(required = false) AuthenticationVo authenticationVo
    ) {
        System.out.println(authenticationVo.getToken());
        return CommonUtils.decorateReturnObject(new ReturnObject(new UserRetVo()));
    }

    /**
     * 修改自己
     *
     * @param authenticationVo token
     * @param nickname         昵称
     * @param realname         真名
     * @param avatar           头像
     * @param birthday         生日
     * @param phone            电话
     * @param gender           性别
     * @return true/false
     */
    @ApiOperation(value = "修改自己")
    @PutMapping("/user")
    public Object updateSelf(
            @ApiParam(name = "authenticationVo", value = "token", required = true)
            @RequestParam AuthenticationVo authenticationVo,
            @ApiParam(name = "nickname", value = "昵称")
            @RequestParam(required = false) String nickname,
            @ApiParam(name = "realname", value = "真名")
            @RequestParam(required = false) String realname,
            @ApiParam(name = "avatar", value = "头像")
            @RequestParam(required = false) String avatar,
            @ApiParam(name = "birthday", value = "生日")
            @RequestParam(required = false) String birthday,
            @ApiParam(name = "phone", value = "电话")
            @RequestParam(required = false) String phone,
            @ApiParam(name = "gender", value = "性别")
            @RequestParam(required = false) Integer gender
    ) {
        System.out.println(authenticationVo.getToken());
        return CommonUtils.decorateReturnObject(new ReturnObject(true));
    }

    /**
     * 分页条件查询用户
     *
     * @param authenticationVo token
     * @param page             页码
     * @param pageSize         页面大小
     * @param name             用户名
     * @param nickname         昵称
     * @param realname         真名
     * @param phone            电话
     * @param gender           性别
     * @param valid            是否有效
     * @return 查询结果
     */
    @ApiOperation(value = "分页条件查询用户")
    @GetMapping("/users")
    public Object selectUsers(
            @ApiParam(name = "authenticationVo", value = "管理员token", required = true)
            @RequestParam AuthenticationVo authenticationVo,
            @ApiParam(name = "page", value = "页码")
            @RequestParam(required = false) Integer page,
            @ApiParam(name = "pageSize", value = "页面大小")
            @RequestParam(required = false) Integer pageSize,
            @ApiParam(name = "name", value = "用户名")
            @RequestParam(required = false) String name,
            @ApiParam(name = "nickname", value = "昵称")
            @RequestParam(required = false) String nickname,
            @ApiParam(name = "realname", value = "真名")
            @RequestParam(required = false) String realname,
            @ApiParam(name = "phone", value = "电话")
            @RequestParam(required = false) String phone,
            @ApiParam(name = "gender", value = "性别")
            @RequestParam(required = false) Integer gender,
            @ApiParam(name = "valid", value = "是否有效")
            @RequestParam(required = false) Integer valid
    ) {
        System.out.println(authenticationVo.getToken());
        return CommonUtils.decorateReturnObject(new ReturnObject(true));
    }

    /**
     * 管理员修改用户
     *
     * @param authenticationVo  token
     * @param name              用户名
     * @param nickname          昵称
     * @param realname          真名
     * @param avatar            头像
     * @param phone             电话
     * @param gender            性别
     * @param honorPoint        荣誉值
     * @param selfControlPoint  自制值
     * @param contributionPoint 贡献值
     * @param walk              行走的经验值
     * @param read              阅读的经验值
     * @param sport             运动的经验值
     * @param art               艺术的经验值
     * @param practice          实践的经验值
     * @param valid             是否有效
     * @return true/false
     */
    @ApiOperation(value = "管理员修改用户")
    @PutMapping("/user/{id}")
    public Object updateUser(
            @PathVariable Long id,
            @ApiParam(name = "authenticationVo", value = "管理员token", required = true)
            @RequestParam AuthenticationVo authenticationVo,
            @ApiParam(name = "name", value = "用户名")
            @RequestParam(required = false) String name,
            @ApiParam(name = "nickname", value = "昵称")
            @RequestParam(required = false) String nickname,
            @ApiParam(name = "realname", value = "真名")
            @RequestParam(required = false) String realname,
            @ApiParam(name = "avatar", value = "头像")
            @RequestParam(required = false) String avatar,
            @ApiParam(name = "phone", value = "电话")
            @RequestParam(required = false) String phone,
            @ApiParam(name = "gender", value = "性别")
            @RequestParam(required = false) Integer gender,
            @ApiParam(name = "honorPoint", value = "荣誉值")
            @RequestParam(required = false) Integer honorPoint,
            @ApiParam(name = "selfControlPoint", value = "自制值")
            @RequestParam(required = false) Long selfControlPoint,
            @ApiParam(name = "contributionPoint", value = "贡献值")
            @RequestParam(required = false) Long contributionPoint,
            @ApiParam(name = "walk", value = "行走的经验值")
            @RequestParam(required = false) Long walk,
            @ApiParam(name = "read", value = "阅读的经验值")
            @RequestParam(required = false) Long read,
            @ApiParam(name = "sport", value = "运动的经验值")
            @RequestParam(required = false) Long sport,
            @ApiParam(name = "art", value = "艺术的经验值")
            @RequestParam(required = false) Long art,
            @ApiParam(name = "practice", value = "实践的经验值")
            @RequestParam(required = false) Long practice,
            @ApiParam(name = "valid", value = "是否有效")
            @RequestParam(required = false) Integer valid
    ) {
        return "";
    }

    /**
     * 管理员删除用户
     *
     * @param id 用户id
     * @param authenticationVo token
     * @return true/false
     */
    @ApiOperation(value = "管理员删除用户")
    @DeleteMapping("/user/{id}")
    public Object deleteUser(
            @PathVariable Long id,
            @ApiParam(name = "authenticationVo", value = "管理员token", required = true)
            @RequestParam AuthenticationVo authenticationVo
    ) {
        return "";
    }
}
