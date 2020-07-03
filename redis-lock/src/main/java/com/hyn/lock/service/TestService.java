package com.hyn.lock.service;

import com.hyn.lock.config.RedisAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: hyn
 * @Date: 2019-12-17 18:23
 */
@Service
public class TestService {
    @RedisAsync(key = "test",expire = 10L,timeUnit = TimeUnit.SECONDS,retryTimes = 3)
    public void xxx(String a) throws InterruptedException {
        System.out.println(a);
        Thread.sleep(5000l);
    }
}
