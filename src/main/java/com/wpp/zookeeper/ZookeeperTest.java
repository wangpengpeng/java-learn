package com.wpp.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class ZookeeperTest implements Watcher {

    public static class MyTransaction extends Transaction {
        protected MyTransaction(ZooKeeper zk) {
            super(zk);
        }
    }

    public static void main(String[] args) throws Exception {

        // 获取zookpeer对象, 连接串中间不能有空格, 否则报错: java.net.UnknownHostException
        ZookeeperTest zookeeperTest = new ZookeeperTest();
        ZooKeeper zooKeeper = new ZooKeeper("crm-master1:2181,crm-master2:2181,crm-slave1:2181", 15000, zookeeperTest);

        Stat stat = new Stat();
        AsyncCallback.DataCallback getCallback = new AsyncCallback.DataCallback() {
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("------------calback----------");
                System.out.println("rc: " + rc);
                System.out.println("rc_code: " + KeeperException.Code.get(rc));
                System.out.println("path: " + path);
                System.out.println("ctx: " + ctx);
                System.out.println("data: " + new String(data));
                KeeperException keeperException = KeeperException.create(KeeperException.Code.get(-4), "/master");
                System.out.println(keeperException);
                System.out.println("------------calback----------");
            }
        };

        AsyncCallback.StatCallback existsCallbak = new AsyncCallback.StatCallback() {
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                System.out.println("------------existscalback----------");
                System.out.println("rc: " + rc);
                System.out.println("rc_code: " + KeeperException.Code.get(rc));
                System.out.println("path: " + path);
                System.out.println("ctx: " + ctx);
                System.out.println("stat: " + stat);
                System.out.println("------------existscalback----------");
            }
        };

        // 同步, 不使用监听器
//        byte[] data = zooKeeper.getData("/master", false, stat);
        // 同步, 使用默认监听器
        byte[] data = zooKeeper.getData("/workers", true, stat);
//        List<String> list = zooKeeper.getChildren("/master", true);
//        for (String l : list) {
//            System.out.println(l);
//        }
        // 同步, 使用自定义监听器
//        byte[] data = zooKeeper.getData("/master", zookeeperTest, stat);

//        System.out.println("----------- data --------  " + new String(data));

//         异步, 不使用监听器
//        zooKeeper.getData("/workers", false, getCallback, 1);
        System.out.println("----------- test --------  ");
//        byte[] data = zooKeeper.getData("/master", zookeeperTest, stat);
//        zooKeeper.getData("/master", zookeeperTest, getCallback, 100);
//        zooKeeper.exists("/master1", false, existsCallbak, 20);
//        System.out.println("date: " + new String(data));
//        List<String> children = zooKeeper.getChildren("/master", false, stat);
//        System.out.println("stat: " + stat);
//        System.out.println("child: " + children);

        // 事务操作
//        Transaction transaction = new MyTransaction(zooKeeper);
//        transaction.delete("/c", -1);
//        transaction.delete("/b", -1);
//        List<OpResult> commit = transaction.commit();
//        for (OpResult opResult : commit) {
//            System.out.println("getType: " + opResult.getType());
//        }
//        zooKeeper.close();
        Thread.sleep(1000000);
    }

    public void process(WatchedEvent watchedEvent) {
            System.out.println("-------- myWatcher ------- " + watchedEvent.toString());
    }
}
