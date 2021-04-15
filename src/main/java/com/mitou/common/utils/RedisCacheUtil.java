package com.mitou.common.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author rice
 * @since 2021-03-25
 */
@Component("redisCache")
public class RedisCacheUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置Hash表、字段、值
     *
     * @param key   : redis hash表名
     * @param field : hash表字段
     * @param value : hash表字段值
     */
    public void hashSet(String key, String field, String value) {
        if (key == null || "".equals(key)) {
            return;
        }
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * @param key   : redis hash表名
     * @param field : hash表字段
     * @return hash表字段值
     */
    public String hashGet(String key, String field) {
        if (key == null || "".equals(key)) {
            return null;
        }
        return (String) stringRedisTemplate.opsForHash().get(key, field);
    }

    public boolean hashExists(String key, String field) {
        if (key == null || "".equals(key)) {
            return false;
        }
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    public long hashSize(String key) {
        if (key == null || "".equals(key)) {
            return 0L;
        }
        return stringRedisTemplate.opsForHash().size(key);
    }

    public void hashDel(String key, String field) {
        if (key == null || "".equals(key)) {
            return;
        }
        stringRedisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 存放普通键值对
     *
     * @param key
     * @param value
     */
    public void setStr(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存放普通键值对并设置过期时间
     *
     * @param key
     * @param value
     * @param l
     * @param timeUnit
     */
    public void setStr(String key, String value, int l, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, l, timeUnit);
    }

    /**
     *
     */
    public void expire(String key, int l, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, l, timeUnit);
    }

    /**
     * 取普通键值对
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 缓存中是否存在key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public boolean deleteStr(String key) {
        return stringRedisTemplate.delete(key);
    }

    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }


}
