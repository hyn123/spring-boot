package com.nn.rabbitmq.controller;

import com.nn.rabbitmq.entity.Mail;
import com.nn.rabbitmq.service.TestService;
import org.apache.log4j.helpers.ThreadLocalMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hyn
 * @Date: 2019-12-29 11:58
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    public Object test(Mail mail){
        testService.send(mail);
        return "发送成功";
    }
}
