package com.wpp.conart.wpp;

public class RunnableTest  implements  Runnable{

    RunnableTest(){
        Thread tt =new Thread(this);
        tt.start();
    }

    @Override
    public void run() {
        System.out.println("RunnableTest");
        System.out.println(Thread.currentThread().getName());
    }
}
