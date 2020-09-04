package com.lt.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 */
@Component
public class DistributedLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获得锁
     * setIfAbsent方法，就是当键不存在的时候，设置，并且该方法可以设置键的过期时间
     */
    public boolean getLock(String lockId, long millisecond) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockId, "lock",
                millisecond, TimeUnit.SECONDS);
        return success != null && success;
    }

    /*
     *   释放锁
     */
    public void releaseLock(String lockId) {
        redisTemplate.delete(lockId);
    }

}
