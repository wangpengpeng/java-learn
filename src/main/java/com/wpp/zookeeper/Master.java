package com.wpp.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

public class Master implements Watcher {
    private ZooKeeper zk;
    private String hostPort;
    private String serverID = Integer.toHexString(new Random().nextInt());
    private boolean isLeader = false;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZk() {
        try {
            zk = new ZooKeeper(hostPort, 15000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopZk() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean checkMaster() throws Exception{

        while (true) {
            Stat stat = new Stat();
            try {
                byte[] data = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverID);
                return true;
            } catch (KeeperException.NoNodeException e) {
                return false;
            } catch (KeeperException.ConnectionLossException e) {
            }
        }
    }

    private void runForMaster() {
        try {
            zk.create("/master", serverID.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) {
        Master master = new Master("crm-master2:2181,crm-slave1:2181,crm-slave2:2181");
        try {
            master.startZk();
            master.runForMaster();
            boolean isMaster =   master.checkMaster();
            System.out.println("isMaster:" + isMaster);

            // 可以执行  get /master，查看在哪个里面有
            Thread.sleep(60000000);
            master.stopZk();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
