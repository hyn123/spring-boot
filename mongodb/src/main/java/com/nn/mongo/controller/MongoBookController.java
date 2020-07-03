package com.nn.mongo.controller;

import com.nn.mongo.entity.Book;
import com.nn.mongo.entity.User;
import com.nn.mongo.service.MongoBooService;
import com.nn.mongo.service.MongoUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2020/4/22 16:41
 */
@RestController
@RequestMapping("mongo")
public class MongoBookController {

    @Autowired
    private MongoBooService service;

    @Autowired
    private MongoUseService useService;

    @RequestMapping("save")
    public String save(Book book){
        return service.saveObj(book);
    }
    @RequestMapping("findAll")
    public Object findAll(){
        return service.findAll();
    }

    @RequestMapping("userSave")
    public String save(User user){
        return useService.saveObj(user);
    }

    @RequestMapping("findAllUser")
    public Object findAllUser(){
        return useService.findAll();
    }
}
