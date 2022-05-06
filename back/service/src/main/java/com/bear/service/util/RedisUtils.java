package com.bear.service.util;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @author moyulingjiu
 */
@Repository
public class RedisUtils {
    /**
     * 加锁尝试间隔（毫秒）
     */
    private static final Integer TRY_INTERVAL = 50;

    /**
     * 解锁脚本，原子操作
     */
    private static final String UNLOCK_SCRIPT =
            "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n"
                    + "then\n"
                    + "    return redis.call(\"del\",KEYS[1])\n"
                    + "else\n"
                    + "    return 0\n"
                    + "end";

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 获取某个变量
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 放入某个变量
     *
     * @param key   key
     * @param value value
     */
    public void put(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取某个对象
     *
     * @param key key
     * @return 对象
     */
    public Object getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取并且删除某个对象
     *
     * @param key key
     * @return 对象
     */
    public Object getAndDeleteObj(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }

    /**
     * 放入某个对象
     *
     * @param key key
     * @param obj 对象
     */
    public void putObj(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }

    /**
     * 加锁，有阻塞（如果加锁不成功会一直尝试加锁，直到timeout）
     *
     * @param name    key
     * @param expire  过期时间（毫秒）
     * @param timeout 等待时间（毫秒）
     * @return token
     */
    public String lock(String name, long expire, long timeout) {
        long startTime = System.currentTimeMillis();
        String token;
        do {
            token = tryLock(name, expire);
            if (token == null) {
                if ((System.currentTimeMillis() - startTime) > (timeout - TRY_INTERVAL)) {
                    break;
                }
                try {
                    Thread.sleep(TRY_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } while (token == null);

        return token;
    }

    /**
     * 加锁，无阻塞（如果无法加锁将直接返回null）
     *
     * @param name   key
     * @param expire 过期时间（毫秒）
     * @return token
     */
    public String tryLock(String name, long expire) {
        String token = UUID.randomUUID().toString();
        RedisConnectionFactory factory = stringRedisTemplate.getConnectionFactory();
        if (factory == null) {
            return null;
        }
        RedisConnection conn = factory.getConnection();
        try {
            Boolean result = conn.set(name.getBytes(StandardCharsets.UTF_8), token.getBytes(StandardCharsets.UTF_8),
                    Expiration.from(expire, TimeUnit.MILLISECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT);
            if (result != null && result) {
                return token;
            }
        } finally {
            RedisConnectionUtils.releaseConnection(conn, factory);
        }
        return null;
    }

    /**
     * 解锁
     *
     * @param name  key
     * @param token token
     * @return 对应的key与token解锁
     */
    public boolean unlock(String name, String token) {
        byte[][] keysAndArgs = new byte[2][];
        keysAndArgs[0] = name.getBytes(StandardCharsets.UTF_8);
        keysAndArgs[1] = token.getBytes(StandardCharsets.UTF_8);
        RedisConnectionFactory factory = stringRedisTemplate.getConnectionFactory();
        if (factory == null) {
            return false;
        }
        RedisConnection conn = factory.getConnection();
        try {
            Long result = conn.scriptingCommands().eval(UNLOCK_SCRIPT.getBytes(StandardCharsets.UTF_8), ReturnType.INTEGER, 1, keysAndArgs);
            if (result != null && result > 0) {
                return true;
            }
        } finally {
            RedisConnectionUtils.releaseConnection(conn, factory);
        }

        return false;
    }
}
