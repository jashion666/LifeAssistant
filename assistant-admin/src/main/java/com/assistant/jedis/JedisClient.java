package com.assistant.jedis;

import redis.clients.jedis.SortingParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * jedis的接口.
 *
 * @author 会写代码的厨师.
 *         * @date : 2018-08-04
 */
public interface JedisClient {

    /**
     * 批量删除对应的value
     *
     * @param keys keys
     */
    void remove(final String... keys);

    /**
     * 批量删除key
     *
     * @param pattern pattern
     */
    void removePattern(final String pattern);


    /**
     * 删除对应的value
     *
     * @param key key
     */
    void remove(final String key);

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key key
     * @return 是否存在key
     */
    boolean exists(final String key);

    /**
     * 读取缓存
     *
     * @param key key
     * @return Object
     */
    Object get(final String key);

    /**
     * 写入缓存
     *
     * @param key   k
     * @param value v
     * @return boolean
     */
    boolean set(final String key, Object value);

    /**
     * 写入缓存 并设置过期时间
     *
     * @param key        k
     * @param value      v
     * @param expireTime 过期时间
     * @return boolean
     */
    boolean set(final String key, Object value, Long expireTime);

    /**
     * 获取自增的值
     *
     * @param key   k
     * @param delta delta
     * @return long
     */
    long increment(final String key, long delta);

}
