package com.lhh.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.concurrent.TimeUnit;

public class WatchClient {
    public static void main(String[] args) throws InterruptedException {
        ZkClient zk = new ZkClient("localhost:2181", 1000, 1000, new SerializableSerializer());
        for (int i = 0; i <10 ; i++) {
            TimeUnit.SECONDS.sleep(2);
            zk.writeData("/data","90"+i);
        }

    }
}
