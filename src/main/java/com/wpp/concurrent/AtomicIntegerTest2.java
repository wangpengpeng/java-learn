package com.wpp.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wpp
 * @Date: 2020/9/22 9:56 下午
 */
public class AtomicIntegerTest2 {

    private  static  AtomicInteger  atomicInteger = new AtomicInteger(2);


    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();



        for(int i=0;i<100;i++){

            Thread thread =new Thread( ()->{
                for(int j=0;j<10000;j++){
                    safeAdd();
//                    safeAddBtyIdx(j);
                }
            });

            thread.start();
        }

        Thread.sleep(3000);

        System.out.println(atomicInteger.get());

    }

    private static void safeAdd(){

        atomicInteger.getAndIncrement();
    }


    private static void safeAddBtyIdx(int idx){

        atomicInteger.getAndAdd(idx);
    }


}
