package com.lhh.corator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZkCoratorTest {
    public static void main(String[] args) throws Exception {
        //解决了节点重复问题 不是一次性
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181",
                new RetryNTimes(3, 1000));
        client.start();
//        client.create().withMode(CreateMode.EPHEMERAL).forPath("/data","100".getBytes());
        String path = "/data";
        //NodeCache 就是对一个节点的缓存
//        NodeCache nodeCache = new NodeCache(client, path);
//        nodeCache.start();
//        nodeCache.getListenable().addListener(new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                System.out.println("1212123");
//            }
//        });

        /**
         * 这里使用的是原生客户端一次性的
         * 只是会判断一次
         */
        client.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("用的是CuratorFramework Watcher");
            }
        }).forPath(path);
        System.in.read();
    }
}
