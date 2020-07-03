package com.nn.mongo.service;

import com.nn.mongo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 2020/4/22 16:39
 */
@Service
public class MongoBooService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String saveObj(Book book) {
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        mongoTemplate.save(book);
        return "添加成功";
    }

    public List<Book> findAll() {
        return mongoTemplate.findAll(Book.class);
    }

}
