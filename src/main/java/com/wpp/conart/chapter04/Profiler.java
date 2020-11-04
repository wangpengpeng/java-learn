package com.wpp.conart.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * 6-15
 */
public class Profiler {
    
    
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<Long>() {
                                                                protected Long initialValue() {
                                                                    return System.currentTimeMillis();
                                                                }
                                                            };

    public static final void begin() {
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
