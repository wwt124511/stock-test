package com.wwt.stocktest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author wwt
 * @ClassName RedisLockUtil.java
 * @Description redis分布式锁工具类
 * @CreateTime 2022-12-17 02:23
 */
@Service
public class RedisLockUtil {

    private String name;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static  final String KEY_PRIFIX = "lock:";

    /**
     * @Author wwt
     * @Description 尝试获取锁
     * @Param timeoutSpc 锁持有的超时时间，过期后自动释放
     * @Return true代表获取锁成功 false代表获取锁失败
     * @CreateTime 2022-12-19 11:22
     */
    public boolean tryLock(String redisKey, String redisValue, long timeoutSpc){
        //获取当前线程标识id
        long threadId = Thread.currentThread().getId();
        //获取锁
        Boolean absent = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PRIFIX + redisKey, redisValue, timeoutSpc, TimeUnit.SECONDS);
        //自动拆箱，避免空指针
        return Boolean.TRUE.equals(absent);

    }

    /**
     * @Author wwt
     * @Description 释放锁
     * @Param
     * @Return
     * @CreateTime 2022-12-19 11:23
     */
    public void unLock(String redisKey){
        stringRedisTemplate.delete(KEY_PRIFIX + redisKey);
    }


}
