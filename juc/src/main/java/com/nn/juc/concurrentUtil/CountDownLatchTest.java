package com.nn.juc.concurrentUtil;

import com.nn.juc.data.LinkedListTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: hyn
 * @Date: 2020-01-10 10:37
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        //countDown();
        print("*",10);
    }
    private static void print(String s,int i){
        if (i>0){
            System.out.println(s);
            print(s+" * *",--i);
        }else{
            return;
        }
    }

    private static void countDown() {
        //模拟10人赛跑。10人跑完后才喊"Game Over."
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch downLatch = new CountDownLatch(10);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"  开始跑");
                        Thread.sleep(1000* finalI);
                        downLatch.countDown();
                        begin.await();
                        System.out.println(Thread.currentThread().getName()+"  结束了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {

                    }
                }
            };
            exec.submit(runnable);
        }
        try {
            downLatch.await();
            begin.countDown();
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
