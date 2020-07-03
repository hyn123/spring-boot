package com.nn.mongo.service;

import com.nn.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2020/4/22 16:39
 */
@Service
public class MongoUseService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String saveObj(User user) {
        mongoTemplate.save(user);
        return "添加成功";
    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

}
