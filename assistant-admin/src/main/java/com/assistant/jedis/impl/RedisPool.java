package com.assistant.jedis.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 作者：会写代码的厨师.
 * 时间：2018-08-04.
 */
public class RedisPool {

    private JedisPoolConfig config;
    private String serverIp;
    private int port;
    private int timeOut;
    private String password;
    private JedisPool pool;

    public void init() {
        pool = new JedisPool(config, serverIp, port, timeOut, password);
    }

    public Jedis getInstance() {
        return pool.getResource();
    }

    public void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

    public void returnBrokenResource(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public JedisPoolConfig getConfig() {
        return config;
    }

    public void setConfig(JedisPoolConfig config) {
        this.config = config;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
