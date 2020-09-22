package com.wpp.conart.wpp;

public class RunnableTest2 implements  Runnable{


    @Override
    public void run() {
        System.out.println("RunnableTest2");
        System.out.println("RunnableTest2: "+ Thread.currentThread().getName());
    }
}
