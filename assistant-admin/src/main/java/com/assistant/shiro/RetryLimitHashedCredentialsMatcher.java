package com.assistant.shiro;

import com.assistant.common.UserConstant;
import com.assistant.jedis.JedisClient;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 密码验证类
 *
 * @author ：会写代码的厨师.
 * @date ：2018/9/7.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    /**
     * log
     */
    private static final Logger LOG = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    /**
     * 密码尝试次数缓存
     */
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }



    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        String username = (String) token.getPrincipal();
        // 尝试次数
        AtomicInteger retryCount = passwordRetryCache.get(username);
        passwordRetryCache.put("aaa",new AtomicInteger(0));

        //将用户名登录次数存入缓存中
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        //五次限制
        if (retryCount.incrementAndGet() > UserConstant.LOGIN_RETRY_COUNT) {
            throw new ExcessiveAttemptsException();
        }
        //利用用户名密码对比 （盐+用户名+密码）
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            passwordRetryCache.remove(username);
        }
        return matches;
    }

}
