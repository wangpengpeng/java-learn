package com.wpp.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.UnhandledErrorListener;

public class CuratorTest {
    public static void main(String[] args) {
        RetryPolicy retryPolicy = null;
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("master1:2181", retryPolicy);
        CuratorListener curatorListener = new CuratorListener() {
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {

            }
        };
        curatorFramework.getCuratorListenable().addListener(curatorListener);
        UnhandledErrorListener unhandledErrorListener = new UnhandledErrorListener() {
            public void unhandledError(String message, Throwable e) {

            }
        };
    }
}
