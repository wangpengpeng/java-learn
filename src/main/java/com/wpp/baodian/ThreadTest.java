package com.wpp.baodian;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wpp
 * @Date: 2020/10/10 11:17 下午
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

       Counter counter = new Counter();

      for (int i=0;i<1000;i++){

          new Thread(new Runnable(){
              @Override
              public void run() {
                  counter.inc();
              }
          }).start();
      }


      Thread.sleep(5000);
      System.out.println(counter.count);

    }


}


 class Counter{
   private ReentrantLock lock = new ReentrantLock();
    public volatile int count =0;

    public void  inc(){
        try{   //如果注释掉，则结果为1000，开启休眠则部位1000
            Thread.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
//        count++;
       try {
           lock.lock();
           count = count + 1;
       }finally {
           lock.unlock();
       }

    }
}



