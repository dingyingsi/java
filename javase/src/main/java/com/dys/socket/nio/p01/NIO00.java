package com.dys.socket.nio.p01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class NIO00 {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8090);
        System.out.println("step1: new ServerSocket(8090)");
        while(true) {
            Socket client = server.accept();
            System.out.println("step2:client\t" + client.getPort());
            new Thread(()->{
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while(true) {
                        System.out.println(reader.readLine());
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
