package com.assistant.jedis.impl;

import com.assistant.jedis.JedisClient;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Jedis 工具实现类
 *
 * @author ：会写代码的厨师.
 * @date ：2018/8/14.
 */
public class JedisClientImpl implements JedisClient {

    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys keys
     */
    @Override
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern pattern
     */
    @Override
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key key
     */
    @Override
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key key
     * @return 是否存在key
     */
    @Override
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key key
     * @return Object
     */
    @Override
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     *
     * @param key   k
     * @param value v
     * @return boolean
     */
    @Override
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存 并设置过期时间
     *
     * @param key        k
     * @param value      v
     * @param expireTime 过期时间
     * @return boolean
     */
    @Override
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取自增的值
     *
     * @param key   k
     * @param delta delta
     * @return long
     */
    @Override
    public long increment(final String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
