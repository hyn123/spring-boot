package com.nn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
    //向redis消息队列index通道发布消息
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Scheduled(fixedRate = 3000)
    public void sendMessage() {
        stringRedisTemplate.convertAndSend("index", "index:"+String.valueOf(Math.random()));
        System.out.println("定时执行中");
    }
}
