package com.nn.juc.concurrentUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * CyclicBarrier 测试
 * @Author: hyn
 * @Date: 2020-01-15 15:43
 */
public class CyclicBarrierTest {

    public static class ParpareWork implements Runnable{

        private CyclicBarrier cyclicBarrier = null;
        public ParpareWork( CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"  已经准备好了");
                Thread.sleep(3000);
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier c = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("开始");
            }
        });
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new ParpareWork(c),"运动员"+i);
            t.start();
        }

    }

}
