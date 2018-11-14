package com.jiao.fast;

import com.jiao.common.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * Created by jiao on 2018/11/12.
 */
public class FastDFSTest {

    @Test
    public void upload() throws Exception{
        // 创建配置文件，文件名随意，内容就是tracker服务的地址
        // 使用一个全局对象来加载这个配置文件
        ClientGlobal.init("E:/gitrepo/java_practice/mall/e3managerweb/src/main/resources/conf/client.conf");
        // 创建一个trackerclient，直接new一个
        TrackerClient trackerClient = new TrackerClient();
        // 通过trackerclient来获取一个trackerserver
        TrackerServer trackerServer = trackerClient.getConnection();
        // 创建一个storageServere的引用 可以为null
        StorageServer storageServer = null;
        // 创建一个storageclient，需要trackerserver和storageServere。
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

    }
    @Test
    public void upload2() throws Exception{

        FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");


        String png = fastDFSClient.uploadFile("C:/Users/jiao/Pictures/backup/keepalived.png", "png", null);
        System.out.println(png);


    }
}
