package com.wpp.zookeeper.redis;

import com.wpp.zookeeper.ConstConf;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: wpp
 * @Date: 2020/9/20 12:36 上午
 */
public class SecKillTest {

    private static String key = "macbook";

    private static String num = "100";

    private static ExecutorService executorService = Executors.newFixedThreadPool(8);


    @Before
    public void before() {
        Jedis jedis = new Jedis(ConstConf.redisHost);

        String script = "redis.call('del',KEYS[1]);return redis.call('set',KEYS[1],ARGV[1])";
        jedis.eval(script, Collections.singletonList(key), Collections.singletonList(num));
        jedis.close();
    }

    @Test
    public void test() {

    }

    public static void main(String[] args) {
        try{
            for (int i = 1; i <= 600; i++) {
                executorService.submit(new SecKillDemo2("顾客"+i,key));
            }
        }finally {
            executorService.shutdown();
        }
    }
}