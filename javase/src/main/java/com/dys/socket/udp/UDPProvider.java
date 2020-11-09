package com.dys.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPProvider {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(20000);
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
        ds.receive(receivePack);

        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLen = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, dataLen);
        System.out.println("UDPProvider receive from ip: " + ip + "\tport: " + port + "\tdata" + data);
        String responseData = "Receive data with len: " + dataLen;
        byte[] reponseDataBytes = responseData.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(reponseDataBytes, reponseDataBytes.length,
                receivePack.getAddress(), receivePack.getPort());

        ds.send(responsePacket);

        System.out.println("UDProvider finished");
        ds.close();
    }

}
