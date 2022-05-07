package com.bear.util;

import com.bear.model.SimplePerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

/**
 * 工具类
 *
 * @author moyulingjiu
 * create 2022年3月26日
 */
public class Common {
    private static final Logger logger = LoggerFactory.getLogger(Common.class);

    /**
     * 时间返回的格式
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    // todo: 生产环境需要修改
    /**
     * 系统初始化密码
     * <p>
     * 当系统没有root账户的时候，将会使用该账户密码初始化root账户
     */
    public static final String INIT_PASSWORD = "123456";
    /**
     * 系统初始化账号
     */
    public static final String INIT_USER_NAME = "admin";

    /**
     * SimplePerson的name字段
     */
    private static final String NAME_FIELD_SUFFIX = "Name";

    /**
     * SimplePerson的id字段
     */
    private static final String ID_FIELD_SUFFIX = "Id";

    /**
     * 修改人的字段
     */
    private static final String MODIFIED = "modified";

    /**
     * 修改时间的字段
     */
    private static final String GMT_MODIFIED = "gmtModified";


    /**
     * 克隆Object
     *
     * @param bo      被克隆的对象
     * @param voClass 克隆的目标对象
     * @param <T>     对象类型
     * @return 克隆之后的对象
     */
    public static <T> T cloneObject(Object bo, Class<T> voClass) {
        if (bo == null) {
            return null;
        }
        Class<?> boClass = bo.getClass();
        T ret = null;

        try {
            ret = voClass.getDeclaredConstructor().newInstance();
            Field[] voFields = voClass.getDeclaredFields();
            HashSet<String> usedField = new HashSet<>();

            for (Field voField : voFields) {
                if (usedField.contains(voField.getName())) {
                    continue;
                }
                // 对于静态变量和final不拷贝
                int modifiers = voField.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }
                // 关掉可达性检查
                voField.setAccessible(true);
                Field boField;
                try {
                    boField = boClass.getDeclaredField(voField.getName());
                    boField.setAccessible(true);

                    if (voField.getType() == boField.getType()) {
                        voField.set(ret, boField.get(bo));
                        continue;
                    }

                    boolean integerByte2Enum = (boField.getType() == Byte.class || boField.getType() == Integer.class) && voField.getType().isEnum();
                    boolean enum2IntegerByte = (voField.getType() == Byte.class || voField.getType() == Integer.class) && boField.getType().isEnum();
                    // 枚举类型与byte之间互相转化
                    if (integerByte2Enum) {
                        Object newObj = boField.get(bo);
                        if (boField.getType() == Byte.class) {
                            newObj = ((Byte) newObj).intValue();
                        }
                        Object[] enumConstants = voField.getType().getEnumConstants();
                        voField.set(ret, enumConstants[(int) newObj]);
                    } else if (enum2IntegerByte) {
                        Object value = ((Enum<?>) boField.get(bo)).ordinal();
                        if (voField.getType() == Byte.class) {
                            value = ((Integer) value).byteValue();
                        }
                        voField.set(ret, value);
                    }
                } catch (NoSuchFieldException ignored) {
                    // 如果找不到对应的字段，那么就需要查看是否是SimplePerson<->(name, id)这种情况
                    if (voField.getType() == SimplePerson.class) {
                        // 对于bo的两个字段，拷贝到vo的一个字段
                        String nameField = voField.getName() + NAME_FIELD_SUFFIX;
                        String idField = voField.getName() + ID_FIELD_SUFFIX;
                        Field boName, boId;
                        try {
                            boName = boClass.getDeclaredField(nameField);
                            boId = boClass.getDeclaredField(idField);
                        } catch (NoSuchFieldException ignored2) {
                            continue;
                        }
                        if (boId.getType() != Long.class || boName.getType() != String.class) {
                            continue;
                        }
                        boName.setAccessible(true);
                        boId.setAccessible(true);
                        SimplePerson simplePerson = new SimplePerson();
                        simplePerson.setId((Long) boId.get(bo));
                        simplePerson.setName((String) boName.get(bo));
                        voField.set(ret, simplePerson);
                    } else {
                        // 对于bo的一个字段，拷贝到vo的两个字段
                        Field voName = null, voId = null;
                        String fieldName = null;
                        if (voField.getName().endsWith(NAME_FIELD_SUFFIX)) {
                            voName = voField;
                            fieldName = voField.getName().substring(0, voField.getName().length() - NAME_FIELD_SUFFIX.length());
                            usedField.add(fieldName + ID_FIELD_SUFFIX);
                            try {
                                voId = voClass.getDeclaredField(fieldName + ID_FIELD_SUFFIX);
                            } catch (NoSuchFieldException ignored2) {
                            }
                        } else if (voField.getName().endsWith(ID_FIELD_SUFFIX)) {
                            voId = voField;
                            fieldName = voField.getName().substring(0, voField.getName().length() - ID_FIELD_SUFFIX.length());
                            usedField.add(fieldName + NAME_FIELD_SUFFIX);
                            try {
                                voName = voClass.getDeclaredField(fieldName + NAME_FIELD_SUFFIX);
                            } catch (NoSuchFieldException ignored2) {
                            }
                        }
                        if (fieldName == null || voName == null || voId == null) {
                            continue;
                        }
                        if (voName.getType() != String.class || voId.getType() != Long.class) {
                            continue;
                        }
                        voName.setAccessible(true);
                        voId.setAccessible(true);
                        Field personField;
                        try {
                            personField = boClass.getDeclaredField(fieldName);
                        } catch (NoSuchFieldException e) {
                            continue;
                        }
                        if (personField.getType() == SimplePerson.class) {
                            personField.setAccessible(true);
                            SimplePerson person = (SimplePerson) personField.get(bo);
                            if (person == null) {
                                continue;
                            }
                            voName.set(ret, person.getName());
                            voId.set(ret, person.getId());
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.debug(e.toString());
        }

        return ret;
    }

    /**
     * 更改类的修改时间
     *
     * @param origin 对象object
     * @param id     修改人id
     * @param name   修改人name
     * @param <T>    Object的类型
     */
    public static <T> void modifyObject(T origin, Long id, String name) {
        Class<?> aClass = origin.getClass();
        // 修改修改人
        try {
            Field modified = aClass.getDeclaredField(MODIFIED);
            modified.setAccessible(true);
            if (modified.getType() == SimplePerson.class) {
                SimplePerson person = null;
                try {
                    person = (SimplePerson) modified.get(origin);
                } catch (IllegalAccessException e) {
                    logger.debug(e.toString());
                }
                if (person == null) {
                    person = new SimplePerson();
                    try {
                        modified.set(origin, person);
                    } catch (IllegalAccessException e) {
                        logger.debug(e.toString());
                    }
                }
                person.setName(name);
                person.setId(id);
            }
        } catch (NoSuchFieldException e) {
            logger.debug(e.toString());
        }
        // 修改修改的时间
        try {
            Field gmtModified = aClass.getDeclaredField(GMT_MODIFIED);
            gmtModified.setAccessible(true);
            if (gmtModified.getType() == LocalDateTime.class) {
                try {
                    gmtModified.set(origin, LocalDateTime.now());
                } catch (IllegalAccessException e) {
                    logger.debug(e.toString());
                }
            }
        } catch (NoSuchFieldException e) {
            logger.debug(e.toString());
        }
        try {
            Field idField = aClass.getDeclaredField(MODIFIED + ID_FIELD_SUFFIX);
            Field nameField = aClass.getDeclaredField(MODIFIED + NAME_FIELD_SUFFIX);
            idField.setAccessible(true);
            nameField.setAccessible(true);
            if (idField.getType() == id.getClass()) {
                try {
                    idField.set(origin, id);
                } catch (IllegalAccessException e) {
                    logger.debug(e.toString());
                }
            }
            if (nameField.getType() == name.getClass()) {
                try {
                    nameField.set(origin, name);
                } catch (IllegalAccessException e) {
                    logger.debug(e.toString());
                }
            }
        } catch (NoSuchFieldException e) {
            logger.debug(e.toString());
        }
    }

    /**
     * 获取秘钥
     *
     * @return 秘钥
     */
    public static String getTokenSecret() {
        // todo: 生产环境更改秘钥
        return "111111";
    }

    /**
     * 获取密码加密的秘钥
     *
     * @return 秘钥
     */
    public static String getPasswordSecret() {
        // todo: 生产环境更改秘钥
        return "111111";
    }

    /**
     * 时间戳
     *
     * @return 当前时间戳
     */
    public static String getTimestamp() {
        DateTimeFormatter dfDateTime = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return dfDateTime.format(LocalDateTime.now());
    }
}
