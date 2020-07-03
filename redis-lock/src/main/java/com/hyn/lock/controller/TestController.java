package com.hyn.lock.controller;

import com.hyn.lock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @Program: redis
 * @ClassName: TestController
 * @Description:
 * @Author: mao'mao
 * @Date: 2019/8/28 16:47
 * @Version: v1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService service;

    @GetMapping("test")
    public void xxx(String a) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("开始线程3");
                    service.xxx("线程三");
                    latch.countDown();
                    Thread.currentThread().wait();
                    System.out.println("结束线程3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("开始线程1");
                    service.xxx("线程一");
                    latch.countDown();
                    Thread.currentThread().wait();
                    System.out.println("结束线程1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("开始线程2");
                    service.xxx("线程二");
                    latch.countDown();
                    Thread.currentThread().wait();
                    System.out.println("结束线程2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        while (latch.getCount()==0){
            System.out.println("结束");
        }

    }
}
