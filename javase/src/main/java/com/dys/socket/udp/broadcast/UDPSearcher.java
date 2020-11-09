package com.dys.socket.udp.broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UDPSearcher {
    private static final int LISTEN_PORT = 30000;

    public static void main(String[] args) throws Exception {
        System.out.println("UDPSearcher Started.");

        Listener listener = listen();
        sendBBroadcast();

        System.in.read();
        List<Device> devices = listener.getDevicsAndClose();

        for (Device device : devices) {
            System.out.println("Device: " + device);
        }

        System.out.println("UDPSearcher Finished.");
    }

    private static Listener listen() throws Exception {
        System.out.println("UDPSearcher start listening");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Listener listener = new Listener(LISTEN_PORT, countDownLatch);
        listener.start();
        countDownLatch.await();
        return listener;
    }

    private static void sendBBroadcast() throws Exception {
        System.out.println("UDPSearcher sendBroadcast started.");
        DatagramSocket ds = new DatagramSocket();
        String requestData = MessageCreator.buildWithPort(LISTEN_PORT);
        byte[] requestDataBytes = requestData.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes, requestDataBytes.length);
        requestPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        requestPacket.setPort(20000);

        ds.send(requestPacket);
        ds.close();

        System.out.println("UPDSearcher started.");

    }

    private static class Device {
        int port;
        String ip;
        String sn;

        public Device(int port, String ip, String sn) {
            this.port = port;
            this.ip = ip;
            this.sn = sn;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "port=" + port +
                    ", ip='" + ip + '\'' +
                    ", sn='" + sn + '\'' +
                    '}';
        }
    }

    private static class Listener extends Thread {
        private final int listenPort;
        private final CountDownLatch countDownLatch;
        private final List<Device> devices = new ArrayList<>();
        private boolean done = false;
        private DatagramSocket ds = null;
        public Listener(int listenPort, CountDownLatch countDownLatch) {
            this.listenPort = listenPort;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
           countDownLatch.countDown();
           try {
                ds = new DatagramSocket(listenPort);
                while(!done) {
                    final byte[] buf = new byte[512];
                    DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
                    ds.receive(receivePack);

                    String ip = receivePack.getAddress().getHostAddress();
                    int port = receivePack.getPort();
                    int dataLen = receivePack.getLength();
                    String data = new String(receivePack.getData(), 0, dataLen);
                    System.out.println("UDPSearcher receive from ip:" + ip + "\tport:" + port + "\tdata: " + data);
                    String sn = MessageCreator.parseSn(data);
                    if (sn != null) {
                        Device device = new Device(port, ip, sn);
                        devices.add(device);
                    }
                }
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
                close();
           }
            System.out.println("UDPSearcher finished");

        }
        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        List<Device> getDevicsAndClose() {
            done = true;
            close();
            return devices;
        }
    }
}
 
