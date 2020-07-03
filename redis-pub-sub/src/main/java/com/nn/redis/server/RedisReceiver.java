package com.nn.redis.server;

import org.springframework.stereotype.Service;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/7/1 19:33
 * @Description 接受订阅的消息
 */
@Service
public class RedisReceiver {

    public void receiveMessage(String message) {

        System.out.println("收到消息：" + message);
    }

}
