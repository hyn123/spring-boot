package com.nn.rediscluster.compent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/7/3 16:23
 * @Description
 */
@RestController
@RequestMapping("redis")
public class RedisCompent {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("put")
    public String put(){
        redisTemplate.opsForValue().set("mozi","123");
        return "添加成功";
    }
    @RequestMapping("delete")
    public String delete(){
        redisTemplate.delete("mozi");
        return "删除成功";
    }
}
