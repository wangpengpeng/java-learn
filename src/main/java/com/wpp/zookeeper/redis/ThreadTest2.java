package com.wpp.zookeeper.redis;

/**
 * @Author: wpp
 * @Date: 2020/9/20 12:30 下午
 */
public class ThreadTest2 {
    public static void main(String[] args)  throws InterruptedException{

        long startTime = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName());

        long sum =0;
        for(long i=0;i<20000000;i++){
            sum =i+sum;
        }
        System.out.println("sum1:" +sum);



        long sum1 =0;
        for(long i=0;i<20000000;i++){
            sum1 =i+sum1;
        }
        System.out.println("sum11:" +sum1);


        long sum2 =0;
        for(long i=0;i<20000000;i++){
            sum2 =i+sum2;
        }
        System.out.println(Thread.currentThread().getName()+",sum2:" +sum2);


        System.out.println("耗时："+ (System.currentTimeMillis()-startTime));




    }
}
