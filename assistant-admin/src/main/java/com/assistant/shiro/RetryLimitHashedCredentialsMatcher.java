package com.assistant.shiro;

import com.assistant.common.UserConstant;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        //将用户名登录次数存入缓存中
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }

        //将retryCount+1得到count
        int count = retryCount.incrementAndGet();

        LOG.info("用户【" + username + "】 尝试登陆，尝试次数为" + count);

        // 五次限制
        if (count > UserConstant.LOGIN_RETRY_COUNT) {
            //将时间倒计时交给前台做，每次调用缓存都会重新刷新缓存时间。
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
