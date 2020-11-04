package com.wpp.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wpp
 * @Date: 2020/10/21 5:22 下午
 */
public class TestLop {


    private volatile int a  =0;

    private volatile long a3  =0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();




        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2");
            }
        },"t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("3");
            }
        },"t3");

        while (true) {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        }
    }
}
