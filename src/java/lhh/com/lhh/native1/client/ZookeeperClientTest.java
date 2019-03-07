package com.lhh.native1.client;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import static org.apache.zookeeper.Watcher.Event.EventType.NodeDataChanged;

/**
 * 原生的代码
 */
public class ZookeeperClientTest {
    public static void main(String[] args) throws Exception {
//        ZooKeeper client = new ZooKeeper("192.168.37.71:2181",
//                5000,
//                event -> System.out.println("event = " + event));
        ZooKeeper client = new ZooKeeper("localhost:2181",
                5000,
                event -> System.out.println("event = " + event));
        Stat stat = new Stat();
        new String(client.getData("/data", event -> {
            //只能判断第一次改动
            if (NodeDataChanged == event.getType()) {
                System.out.println("数据发生了改变");
            }
        },stat));
        System.in.read();
    }
}
