/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.wpp.conart.chapter06;

import java.util.HashMap;
import java.util.UUID;
import  java.lang.reflect.Proxy;

/**
 * ����put
 * 
 * @author tengfei.fangtf
 * @version $Id: Snippet.java, v 0.1 2015-7-31 ����11:53:55 tengfei.fangtf Exp $
 */
public class ConcurrentPutHashMap {

    public static void main(String[] args) throws InterruptedException {
    	
    	System.out.println("dsa");
    	
        final HashMap<String, String> map = new HashMap<String, String>(2);
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                	//��10������
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                        	String uuid = UUID.randomUUID().toString();
                        	System.out.println("FOR : "+uuid );
                 
                            map.put(uuid, "11");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
//        t.join();
        
        System.out.println("end");
         
    }

}
