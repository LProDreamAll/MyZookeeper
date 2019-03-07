package com.lhh.corator;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自定义领导者选举
 * 这种方式用的临时节点
 */
public class LeaderLatchExample {
    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> leaderLatchs = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181",
                    new RetryNTimes(3, 1000));
            clients.add(client);
            client.start();

            LeaderLatch leaderLatch = new LeaderLatch(client, "/LeaderLatch", "client#" + i);
            leaderLatchs.add(leaderLatch);
            leaderLatch.start();
        }
        TimeUnit.SECONDS.sleep(5);
        for (LeaderLatch leaderLatch : leaderLatchs) {
            if (leaderLatch.hasLeadership()) {
                //每次都不相同
                System.out.println(leaderLatch.getId() + "is leader");
                break;
            }
        }
        for (CuratorFramework curatorFramework : clients) {
            curatorFramework.close();
        }
    }
}
