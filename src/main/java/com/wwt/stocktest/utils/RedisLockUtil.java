package com.wwt.stocktest.utils;

import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    private static final String KEY_PRIFIX = "lock:";
    private static final String UUID_PRIFIX = UUID.randomUUID().toString(true) + "-";
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    /**
     * @Author wwt
     * @Description 尝试获取锁
     * @Param timeoutSpc 锁持有的超时时间，过期后自动释放
     * @Return true代表获取锁成功 false代表获取锁失败
     * @CreateTime 2022-12-19 11:22
     */
    public boolean tryLock(String lockKey, long timeoutSpc) {
        //获取当前线程锁的标识
        String lockValue = UUID_PRIFIX + Thread.currentThread().getId();
        //获取锁
        Boolean absent = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PRIFIX + lockKey, lockValue, timeoutSpc, TimeUnit.SECONDS);
        //自动拆箱，避免空指针
        return Boolean.TRUE.equals(absent);

    }


    public boolean tryLock(String lockKey) {
        //获取当前线程锁的标识
        String lockValue = UUID_PRIFIX + Thread.currentThread().getId();
        //获取锁
        Boolean absent = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PRIFIX + lockKey, lockValue, TimeUnit.SECONDS.toSeconds(10), TimeUnit.SECONDS);
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
    public void unLock(String lockKey) {
        //调用lua脚本
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PRIFIX + lockKey),
                UUID_PRIFIX + Thread.currentThread().getId());
//        //获取当前线程标识id
//        String threadId = UUID_PRIFIX + Thread.currentThread().getId();
//        //获取锁中的标识
//        String lockValue = stringRedisTemplate.opsForValue().get(KEY_PRIFIX + lockKey);
//        if(threadId.equals(lockValue)){
//            stringRedisTemplate.delete(KEY_PRIFIX + lockKey);
//        }
    }


}
