package com.jessee.nio;

/**
 * Created by jessee on 2018/12/2.
 */
public class Server {


    public static void main(String[] args){
        ServerHandle serverHandle = new ServerHandle(8081);
        Thread thread = new Thread(serverHandle);
        thread.start();
    }
}
