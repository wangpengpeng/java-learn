package com.wpp.concurrent.sellerticket;

/**
 * @Author: wpp
 * @Date: 2020/9/22 11:37 下午
 */
public class Seller implements Runnable {

    private volatile  int ticket=100;

    @Override
    public void run() {

        for(int i=0;i<200;i++){
            if(this.ticket>0){
                System.out.println(Thread.currentThread().getName()+"卖票：ticket:"+this.ticket--);
            }
        }
    }

    public static void main(String[] args) {

//      下面是要卖二十张车票
//        Thread thread1 = new Thread(new Seller(),"t1");
//        thread1.start();
//
//        Thread thread2 = new Thread(new Seller(),"t2");
//        thread2.start();

//        资源共享,只卖十张
        Seller seller  =new Seller();
        Thread thread11 = new Thread(seller,"t11");
        thread11.start();

        Thread thread22 = new Thread(seller,"t22");
        thread22.start();
    }
}
