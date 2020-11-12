package com.dys.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSearcher {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        String requestData = "Helloworld";
        byte[] requestDataBytes = requestData.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestData.length());
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);
        ds.send(requestPacket);

        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
        ds.receive(receivePack);

        String ip = requestPacket.getAddress().getHostAddress();
        int port = requestPacket.getPort();
        int dataLen = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, dataLen);
        System.out.println("UDPSearcher receive from ip: " + ip + "\tport: " + port + "\tdata" + data);
        System.out.println("UDPSearcher finished");
        ds.close();
    }
}
