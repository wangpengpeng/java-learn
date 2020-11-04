package com.wpp.base;

import javax.swing.plaf.TableHeaderUI;

/**
 * @Author: wpp
 * @Date: 2020/9/27 5:42 下午
 *
 * String 对象有个对象池，如果两个是一样的，则只有一个对象。
 *
 * 创建两个对象。
 * String  str = new String("abc")
 */
public class Test {

    private  static int aa = 10;
    public static void main(String[] args) throws InterruptedException {


        String a ="abc";

        String b ="abc";

        System.out.println(a.hashCode() == b.hashCode());

        System.out.println(aa);

        Thread.sleep(1000_000);

    }

    public static  void test(){
        int b = 0;

        System.out.println("444");
    }
}
