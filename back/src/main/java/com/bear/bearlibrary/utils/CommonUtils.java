package com.bear.bearlibrary.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 工具类
 *
 * @author 墨羽翎玖
 */
public class CommonUtils {
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 根据clazz实例化一个对象，并深度克隆bo中对应属性到这个新对象
     * 其中会自动实现modifiedBy和createdBy两字段的类型转换
     *
     * @param bo      business object
     * @param voClass vo对象类型
     * @return 浅克隆的vo对象
     * @author 墨羽翎玖
     */
    public static <T> T cloneVo(Object bo, Class<T> voClass) {
        Class boClass = bo.getClass();
        T newVo = null;
        try {
            //默认voClass有无参构造函数
            newVo = voClass.getDeclaredConstructor().newInstance();
            Field[] voFields = voClass.getDeclaredFields();
            Field[] boFields = boClass.getDeclaredFields();
            for (Field voField : voFields) {
                //静态和Final不能拷贝
                int mod = voField.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                voField.setAccessible(true);
                Field boField = null;
                try {
                    boField = boClass.getDeclaredField(voField.getName());
                    boField.setAccessible(true);
                }
                //bo中查找不到对应的属性，那就有可能为特殊情况xxx，需要由xxxId与xxxName组装
                catch (NoSuchFieldException e) {
                    //提取头部
                    String head = voField.getName();
                    Field boxxxNameField = null;
                    Field boxxxIdField = null;
                    for (Field bof : boFields) {
                        if (bof.getName().matches(head + "Name")) {
                            boxxxNameField = bof;
                        } else if (bof.getName().matches(head + "Id")) {
                            boxxxIdField = bof;
                        }
                    }
                    //找不到xxxName或者找不到xxxId
                    if (boxxxNameField == null || boxxxIdField == null) {
                        voField.set(newVo, null);
                        continue;
                    }

                    Object newSimpleRetVo = voField.getType().getDeclaredConstructor().newInstance();
                    Field newSimpleRetVoIdField = newSimpleRetVo.getClass().getDeclaredField("id");
                    Field newSimpleRetVoNameField = newSimpleRetVo.getClass().getDeclaredField("name");
                    newSimpleRetVoIdField.setAccessible(true);
                    newSimpleRetVoNameField.setAccessible(true);

                    //bo的xxxId和xxxName组装为SimpleRetVo的id,name
                    boxxxIdField.setAccessible(true);
                    boxxxNameField.setAccessible(true);
                    Object boxxxId = boxxxIdField.get(bo);
                    Object boxxxName = boxxxNameField.get(bo);

                    newSimpleRetVoIdField.set(newSimpleRetVo, boxxxId);
                    newSimpleRetVoNameField.set(newSimpleRetVo, boxxxName);

                    voField.set(newVo, newSimpleRetVo);
                    continue;
                }
                Class<?> boFieldType = boField.getType();
                //属性名相同，类型相同，直接克隆
                if (voField.getType().equals(boFieldType)) {
                    boField.setAccessible(true);
                    Object newObject = boField.get(bo);
                    voField.set(newVo, newObject);
                }
                //属性名相同，类型不同
                else {
                    boolean boFieldIsIntegerOrByteAndVoFieldIsEnum = ("Integer".equals(boFieldType.getSimpleName()) || "Byte".equals(boFieldType.getSimpleName())) && voField.getType().isEnum();
                    boolean voFieldIsIntegerOrByteAndBoFieldIsEnum = ("Integer".equals(voField.getType().getSimpleName()) || "Byte".equals(voField.getType().getSimpleName())) && boFieldType.isEnum();
                    boolean voFieldIsLocalDateTimeAndAndBoFieldIsZonedDateTime = ("LocalDateTime".equals(voField.getType().getSimpleName()) && "ZonedDateTime".equals(boField.getType().getSimpleName()));
                    boolean voFieldIsZonedDateTimeAndBoFieldIsLocalDateTime = ("ZonedDateTime".equals(voField.getType().getSimpleName()) && "LocalDateTime".equals(boField.getType().getSimpleName()));

                    try {
                        //整形或Byte转枚举
                        if (boFieldIsIntegerOrByteAndVoFieldIsEnum) {
                            Object newObj = boField.get(bo);
                            if ("Byte".equals(boFieldType.getSimpleName())) {
                                newObj = ((Byte) newObj).intValue();
                            }
                            Object[] enumer = voField.getType().getEnumConstants();
                            voField.set(newVo, enumer[(int) newObj]);
                        }
                        //枚举转整形或Byte
                        else if (voFieldIsIntegerOrByteAndBoFieldIsEnum) {
                            Object value = ((Enum) boField.get(bo)).ordinal();
                            if ("Byte".equals(voField.getType().getSimpleName())) {
                                value = ((Integer) value).byteValue();
                            }
                            voField.set(newVo, value);
                        }
                        //ZonedDateTime转LocalDateTime
                        else if (voFieldIsLocalDateTimeAndAndBoFieldIsZonedDateTime) {
                            ZonedDateTime newObj = (ZonedDateTime) boField.get(bo);
                            LocalDateTime localDateTime = newObj.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
                            voField.set(newVo, localDateTime);
                        }
                        //LocalDateTime转ZonedDateTime
                        else if (voFieldIsZonedDateTimeAndBoFieldIsLocalDateTime) {
                            LocalDateTime newObj = (LocalDateTime) boField.get(bo);
                            ZonedDateTime zdt = newObj.atZone(ZoneId.systemDefault());
                            voField.set(newVo, zdt);
                        } else {
                            voField.set(newVo, null);
                        }
                    }
                    //如果为空字段则不复制
                    catch (Exception e) {
                        voField.set(newVo, null);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return newVo;
    }

    /**
     * 根据 errCode 修饰 API 返回对象的 HTTP Status
     *
     * @param returnObject 原返回 Object
     * @return 修饰后的返回 Object
     */
    public static Object decorateReturnObject(ReturnObject returnObject) {
        switch (returnObject.getCode()) {
            case RESOURCE_ID_NOTEXIST:
                // 404：资源不存在
                return new ResponseEntity(
                        ResponseUtil.fail(returnObject.getCode(), returnObject.getMsg()),
                        HttpStatus.NOT_FOUND);

            case AUTH_INVALID_JWT:
            case AUTH_JWT_EXPIRED:
            case AUTH_USER_FORBIDDEN:
            case AUTH_INVALID_ACCOUNT:
                // 401
                return new ResponseEntity(
                        ResponseUtil.fail(returnObject.getCode(), returnObject.getMsg()),
                        HttpStatus.UNAUTHORIZED);

            case INTERNAL_SERVER_ERR:
                // 500：数据库或其他严重错误
                return new ResponseEntity(
                        ResponseUtil.fail(returnObject.getCode(), returnObject.getMsg()),
                        HttpStatus.INTERNAL_SERVER_ERROR);

            case FIELD_NOTVALID:
            case IMG_FORMAT_ERROR:
            case IMG_SIZE_EXCEED:
            case LATE_BEGINTIME:
            case PARAMETER_MISSED:
                // 400
                return new ResponseEntity(
                        ResponseUtil.fail(returnObject.getCode(), returnObject.getMsg()),
                        HttpStatus.BAD_REQUEST);

            case RESOURCE_ID_OUTSCOPE:
            case FILE_NO_WRITE_PERMISSION:
            case AUTH_NO_RIGHT:
            case AUTH_NEED_LOGIN:
                // 403
                return new ResponseEntity(
                        ResponseUtil.fail(returnObject.getCode(), returnObject.getMsg()),
                        HttpStatus.FORBIDDEN);

            case OK:
                // 200: 无错误
                Object data = returnObject.getData();
                if (data != null) {
                    return ResponseUtil.ok(data);
                } else {
                    return ResponseUtil.ok();
                }

            default:
                data = returnObject.getData();
                if (data != null) {
                    return ResponseUtil.fail(returnObject.getCode(), returnObject.getMsg(), returnObject.getData());
                } else {
                    return ResponseUtil.fail(returnObject.getCode(), returnObject.getMsg());
                }
        }
    }
}
