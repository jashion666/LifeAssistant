package com.assistant.shiro;

import com.assistant.jedis.JedisClient;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.crazycake.shiro.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import java.io.Serializable;
import java.util.*;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/9/10.
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate redisTemplate;

    @Autowired
    private JedisClient client;

    private String keyPrefix =  "shiro_redis:";

    public RedisCache(RedisTemplate redisTemplate) {
        super();
        //构造函数传递redisTemplate过来
        this.redisTemplate = redisTemplate;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }


    /**
     * 获得byte[]型的key
     * @param key
     * @return
     */
    private byte[] getByteKey(Object key){
        if(key instanceof String){
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
        }else{
            return SerializeUtils.serialize((Serializable) key);
        }
    }

    private RedisConnection getRedisConnect() {
        return redisTemplate.getConnectionFactory().getConnection();
    }

    @Override
    public Object get(Object key) throws CacheException {
        byte[] bytes = getByteKey(key);
        byte[] value = getRedisConnect().get(bytes);
        if(value == null){
            return null;
        }
        return SerializeUtils.deserialize(value);
    }

    /**
     * 将shiro的缓存保存到redis中
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
        RedisConnection redisConnection = getRedisConnect();
        redisConnection.set(getByteKey(key), SerializeUtils.serialize((Serializable)value));
        byte[] bytes = redisConnection.get(getByteKey(key));
        Object object = SerializeUtils.deserialize(bytes);

        return object;

    }

    @Override
    public Object remove(Object key) throws CacheException {
        RedisConnection redisConnection = getRedisConnect();

        byte[] bytes = redisConnection.get(getByteKey(key));

        redisConnection.del(getByteKey(key));

        return SerializeUtils.deserialize(bytes);
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
        RedisConnection redisConnection = getRedisConnect();
        redisConnection.flushDb();
    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
        RedisConnection redisConnection = getRedisConnect();
        Long size = redisConnection.dbSize();
        return size.intValue();
    }

    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
        RedisConnection redisConnection = getRedisConnect();
        Set<byte[]> keys = redisConnection.keys(new String("*").getBytes());
        Set<Object> set = new HashSet<Object>();
        for (byte[] bs : keys) {
            set.add(SerializeUtils.deserialize(bs));
        }
        return set;
    }


    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {
        RedisConnection redisConnection = getRedisConnect();
        Set keys = this.keys();

        List<Object> values = new ArrayList<Object>();
        for (Object key : keys) {
            byte[] bytes = redisConnection.get(getByteKey(key));
            values.add(SerializeUtils.deserialize(bytes));
        }
        return values;
    }

}
