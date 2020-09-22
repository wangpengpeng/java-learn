package com.wpp.bjsxt.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: wpp
 * @Date: 2020/9/20 2:45 下午
 *
 * 执行三秒，超过时间变更数据
 */
public class ExecutorServiceTest {

    private static Logger log = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

//    private static ExecutorService executorService= Executors.newFixedThreadPool(5);
    private static ExecutorService executorService= Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        try {
            for(int i=0;i<10;i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        log.info("111当前线程名字:" + Thread.currentThread().getName());

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }


            for(int j=0;j<10;j++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        log.info("222当前线程名字:" + Thread.currentThread().getName());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
            executorService.execute(new Task(2, "33"));

            Future<Integer>  future = executorService.submit(new CallableTest());
            System.out.println("返回结果值：" +future.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
