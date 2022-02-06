package com.bear.bearlibrary.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回的错误码
 *
 * @author 墨羽翎玖
 */
public enum ReturnNo {
    //不加说明的状态码为200或201（POST）
    OK(0,"成功"),
    /***************************************************
     *    系统级错误
     **************************************************/
    //状态码 500
    INTERNAL_SERVER_ERR(500,"服务器内部错误"),

    //所有需要登录才能访问的API都可能会返回以下错误
    //状态码 401
    AUTH_INVALID_JWT(501,"JWT不合法"),
    AUTH_JWT_EXPIRED(502,"JWT过期"),

    //以下错误码提示可以自行修改
    //--------------------------------------------
    //状态码 400
    FIELD_NOTVALID(503,"字段不合法"),
    IMG_FORMAT_ERROR(508,"图片格式不正确"),
    IMG_SIZE_EXCEED(509,"图片大小超限"),
    PARAMETER_MISSED(510, "缺少必要参数"),
    LATE_BEGINTIME(947, "开始时间不能晚于结束时间"),


    //所有路径带id的API都可能返回此错误
    //状态码 404
    RESOURCE_ID_NOTEXIST(504,"操作的资源id不存在"),

    //状态码 403
    RESOURCE_ID_OUTSCOPE(505,"操作的资源id不是自己的对象"),
    FILE_NO_WRITE_PERMISSION(506,"目录文件夹没有写入的权限"),

    //状态码 200
    STATENOTALLOW(507,"当前状态禁止此操作"),
    RESOURCE_FALSIFY(511, "信息签名不正确"),

    CUSTOMER_FORBIDDEN(610,"用户被禁止登录"),
    /***************************************************
     *    权限模块错误码
     **************************************************/
    //状态码 401
    AUTH_INVALID_ACCOUNT(700, "用户名不存在或者密码错误"),
    AUTH_ID_NOTEXIST(701,"登录用户id不存在"),
    AUTH_USER_FORBIDDEN(702,"用户被禁止登录"),

    //状态码 403
    AUTH_NEED_LOGIN(704, "需要先登录"),
    AUTH_NO_RIGHT(705, "无权限"),

    //状态码 200或201
    USER_NAME_REGISTERED( 731,"用户名已被注册"),
    EMAIL_REGISTERED(732, "邮箱已被注册"),
    MOBILE_REGISTERED(733,"电话已被注册"),
    GROUP_EXIST(735, "组名在部门内已存在"),
    ROLE_EXIST(736, "角色名在部门内已存在"),

    PASSWORD_SAME(741,"不能与旧密码相同"),
    URL_SAME(742,"权限url与RequestType重复"),
    PRIVILEGE_SAME(743,"权限名称重复"),

    EMAIL_WRONG(745,"与系统预留的邮箱不一致"),
    MOBILE_WRONG(746,"与系统预留的电话不一致"),
    USERPROXY_CONFLICT(747,"同一时间段有冲突的代理关系"),
    EMAIL_NOTVERIFIED(748,"Email未确认"),
    MOBILE_NOTVERIFIED(749,"电话号码未确认"),
    USERPROXY_BIGGER(750,"开始时间要小于失效时间"),
    USERPROXY_SELF(751,"自己不可以代理自己"),
    USERPROXY_DEPART_CONFLICT(752,"两个代理双方的部门冲突"),
    USERPROXY_DEPART_MANAGER_CONFLICT(753,"管理员无此部门权限"),
    ROLE_RELATION_EXIST(754, "重复继承角色"),
    GROUP_RELATION_EXIST(755, "重复定义用户组"),
    PRIVILEGE_RELATION_EXIST(756, "重复定义权限");

    private int code;
    private String message;
    private static final Map<Integer, ReturnNo> returnNoMap;
    static {
        returnNoMap = new HashMap();
        for (ReturnNo enum1 : values()) {
            returnNoMap.put(enum1.code, enum1);
        }
    }
    ReturnNo(int code, String message){
        this.code = code;
        this.message = message;
    }
    public static ReturnNo getReturnNoByCode(int code){
        return returnNoMap.get(code);
    }
    public int getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }

}
