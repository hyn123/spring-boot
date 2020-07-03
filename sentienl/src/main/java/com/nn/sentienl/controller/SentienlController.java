package com.nn.sentienl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/7/1 17:39
 * @Description
 */
@RestController
@RequestMapping("/demo")
public class SentienlController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
