package com.wpp.bjsxt.ThreadPoolExecutor;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @Author: wpp
 * @Date: 2020/9/20 7:24 下午
 */
public class CallableTest implements Callable {

    @Override
    public Object call() throws Exception {

        System.out.println("和runnable接口类似，只是变成一个返回结果数据");

        return new Random().nextInt();
    }
}
