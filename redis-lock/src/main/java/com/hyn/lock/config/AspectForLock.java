package com.hyn.lock.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hyn
 * @Date: 2019-12-17 11:24
 */
@Component
@Aspect
public class AspectForLock {
    @Autowired
    private RedisLock redisLock;

    @Pointcut("@annotation(com.hyn.lock.config.RedisAsync)")
    public void cut() {

    }

    @Around("cut()&&@annotation(redisAsync)")
    public void around(ProceedingJoinPoint joinPoint, RedisAsync redisAsync) throws Throwable {
        Long expire = redisAsync.expire();
        String lockKey = redisAsync.key();
        TimeUnit timeUnit = redisAsync.timeUnit();
        int retryTimes = redisAsync.retryTimes();
        long waitingTime = redisAsync.retryWaitingTime();
        //这个值可以根据业务定制 必须唯一
        String lockValue= UUID.randomUUID().toString();
        Boolean flag = redisLock.trylockWithTime(expire, lockKey, lockValue, timeUnit,retryTimes,waitingTime);
        if (!flag) {
            return;
        }
        try {
            joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            redisLock.unlock(retryTimes, lockValue, lockKey);
        }
    }
}
