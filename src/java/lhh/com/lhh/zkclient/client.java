package com.lhh.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;

/**
 * 使用zk自带的client
 */
public class client {
    public static void main(String[] args) throws IOException {
        ZkClient zk = new ZkClient("localhost:2181", 1000, 1000, new SerializableSerializer());
//        zk.createPersistent("/data","1".getBytes());
        zk.subscribeDataChanges("/data", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("数据被改变了");
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

            }
        });

        System.in.read();
    }
}
