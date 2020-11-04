/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.wpp.conart.chapter02;

import com.wpp.conart.chapter04.Profiler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������
 * 
 * @author tengfei.fangtf
 * @version $Id: Snippet.java, v 0.1 2015-7-31 ����11:32:42 tengfei.fangtf Exp $
 */
public class Counter {

    private AtomicInteger atomicI = new AtomicInteger(0);

    private int    i       = 0;

    public static void main(String[] args) {


        Profiler.begin();
        final Counter cas = new Counter();

        List<Thread> ts = new ArrayList<Thread>(600);

        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        //先把线程放到list，在遍历重新启动
        for (Thread t : ts) {
            t.start();

        }
        // �ȴ������߳�ִ�����
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());


        System.out.println("日志停止：" +  Profiler.end());
        System.out.println(System.currentTimeMillis() - start);


    }

    /**
     * ʹ��CASʵ���̰߳�ȫ������
     */
    private void safeCount() {
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * ���̰߳�ȫ������
     */
    private void count() {
        i++;
    }

}
