package com.nn.juc.concurrentUtil;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join框架
 * @Author: hyn
 * @Date: 2020-01-14 15:22
 */
public class ForkJoinTest {
    private static class SumTask extends RecursiveTask<Integer> {
        private int[] src ;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex-fromIndex<src.length/10){
                Integer count = 0;
                for (int i = fromIndex; i <=toIndex ; i++) {
                    count += src[i];
                }
                return count;
            }else {
                int mid = (toIndex+fromIndex)/2;
                SumTask task1 = new SumTask(src,fromIndex,mid);
                SumTask task2 = new SumTask(src,mid+1,toIndex);
                invokeAll(task1,task2);
                return task1.join()+task2.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] src = makeArray();
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(src,0,src.length-1);
        long l = System.currentTimeMillis();
        Integer invoke = pool.invoke(task);
        System.out.println("结果为："+invoke);
        System.out.println("用时："+(System.currentTimeMillis()-l));
    }

    public static int[] makeArray() {
        int  ARRAY_LENGTH = 100000;
        //new一个随机数发生器
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for(int i=0;i<ARRAY_LENGTH;i++){
            //用随机数填充数组
            result[i] =  r.nextInt(ARRAY_LENGTH*3);
        }
        return result;

    }
}
