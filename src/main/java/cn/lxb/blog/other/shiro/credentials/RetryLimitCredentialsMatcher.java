package cn.lxb.blog.other.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * shiro限制用户登录尝试次数，防止多次尝试，暴力破解密码情况出现。
 * 要限制用户登录尝试次数，必然要对用户名密码验证失败做记录，
 * Shiro中用户名密码的验证交给了CredentialsMatcher,
 * 所以在CredentialsMatcher里面检查，记录登录次数是最简单的做法。
 * Shiro天生和Ehcache是一对好搭档，无论是单机还是集群，都可以在Ehcache中存储登录尝试次数信息。
 * 现在介绍一个简单的登录次数验证做法，实现一个RetryLimitCredentialsMatchers继承至HashedCredentialsMatcher，
 * 加入缓存，在每次验证用户名密码之前先验证用户名尝试次数，如果超过3次就抛出尝试过多异常，否则验证用户名密码，
 * 验证成功把尝试次数清零，不成功则直接退出。这里依靠Ehcache自带的timeToIdleSeconds来保证锁定时间（
 * 帐号锁定之后的最后一次尝试间隔timeToIdleSeconds秒之后自动清除）。
 * Created by 54LXB on 2017-07-11.
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //密码重试次数加1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        // 如果重试次数超过三次，则锁定账户
        if (retryCount.incrementAndGet() > 3) {
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 3 times in period");
        }
        // 账号密码输入是否正确
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            // 清楚密码重试次数
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
