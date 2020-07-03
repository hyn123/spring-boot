package com.nn.juc.multiThread;

import java.util.concurrent.*;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/28 12:14
 * @Description
 */
public class CallableTest implements Callable<String> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {

        return "实现callable接口";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CallableTest test = new CallableTest();
        Future<String> submit = executorService.submit(test);
        System.out.println(submit.get());
    }
}
