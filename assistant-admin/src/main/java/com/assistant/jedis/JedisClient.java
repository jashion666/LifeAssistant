package com.assistant.jedis;

/**
 * jedis的接口.
 *
 * @author 会写代码的厨师.
 */
public interface JedisClient {
    /**
     * 普通的key
     *
     * @param key
     * @return
     */
    String get(String key);

    String set(String key, String value);

    /**
     * hash key
     *
     * @param hkey
     * @param key
     * @return
     */
    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    /**
     * 递增
     *
     * @param key
     * @return
     */
    long incr(String key);

    /**
     * 设置存活时间
     *
     * @param key
     * @param second
     * @return
     */
    long expire(String key, int second);

    /**
     * 命令以秒为单位返回 key 的剩余过期时间
     *
     * @param key
     * @return
     */
    long ttl(String key);

    long del(String key);

    /**
     * hash delete
     *
     * @param hkey
     * @param key
     * @return
     */
    long hdel(String hkey, String key);
}
