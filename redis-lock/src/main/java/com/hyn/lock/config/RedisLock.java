package com.hyn.lock.config;

import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisGeoCommands;
import io.lettuce.core.api.sync.RedisHashCommands;
import io.lettuce.core.cluster.RedisClusterURIUtil;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Program: redis
 * @ClassName: RedisLock2
 * @Description:          https://blog.csdn.net/qq_36559868/article/details/98174986
 * @Author: mao'mao
 * @Date: 2019/8/28 16:34
 * @Version: v1.0
 */
@Component
@Slf4j
@SuppressWarnings("all")
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 带超时时间的锁
     *
     * @param expireTime
     * @param lockKey
     * @param lockValue
     * @param retryTimes 获取锁的次数
     * @return
     */
    public Boolean trylockWithTime(Long expireTime, String lockKey, String lockValue, TimeUnit timeUnit,int retryTimes,long waitingTime ) throws InterruptedException {
        //过期时间点
        Long invalidTime = System.currentTimeMillis() + expireTime * 1000;
        //不断尝试获取锁 直到超时
        boolean flag = false;
        while (retryTimes==-1 || 0<retryTimes) {
            flag = tryLock(lockKey, lockValue, expireTime,timeUnit);
            if (flag) {
                log.info("获得锁成功");
                break;
            }else {
                retryTimes--;
            }
            timeUnit.sleep(waitingTime);
        }
        if (flag==false){
            log.info("竞争锁失败");
        }
        return flag;
    }


    /**
     * 解锁
     *
     * @param retryTimes
     * @param lockValue
     * @param lockKey
     * @return
     */
    public Boolean unlock(Integer retryTimes, String lockValue, String lockKey) {
        String valueOfKey = (String) redisTemplate.opsForValue().get(lockKey);
        Boolean flag = false;
        if (lockValue.equals(valueOfKey)) {
            while (retryTimes==-1||0<retryTimes) {
                try {
                    flag = redisTemplate.delete(lockKey);
                    log.info("释放锁成功");
                    break;
                } catch (Exception e) {
                    log.error("解锁失败");
                    retryTimes--;
                }

            }
        }
        return flag;
    }


    /**
     * 上锁
     *
     * @param lockKey
     * @param lockValue
     * @param expireTime
     * @return
     */
    private Boolean tryLock(String lockKey, String lockValue, Long expireTime,TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, expireTime, timeUnit);
    }
}
