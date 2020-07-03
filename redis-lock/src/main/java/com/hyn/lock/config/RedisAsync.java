package com.hyn.lock.config;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hyn
 * @Date: 2019-12-17 11:23
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisAsync {
    /**
     * 锁名称
     * @return
     */
    String key() default "";

    /**
     * 超时时间
     * @return
     */
    long expire() default 0L;

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 重试次数
     * @return
     */
    int retryTimes() default -1;

    /**
     * 重试等待时间
     * @return
     */
    long retryWaitingTime() default 10;
}
