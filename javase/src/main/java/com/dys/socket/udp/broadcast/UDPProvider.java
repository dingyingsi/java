package com.dys.socket.udp.broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;

public class UDPProvider {
    public static void main(String[] args) throws Exception {

        String sn = UUID.randomUUID().toString();
        Provider provider = new Provider(sn);
        provider.start();
        System.in.read();
        provider.exit();

    }
    private static class Provider extends Thread {
        private final String sn;
        private boolean done = false;
        private DatagramSocket ds = null;

        public Provider(String sn) {
            this.sn = sn;
        }
        @Override
        public void run() {
            System.out.println("UDPProvider Started.");
            try {
                ds = new DatagramSocket(20000);

                while (!done) {

                    final byte[] buf = new byte[512];
                    DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
                    ds.receive(receivePack);

                    String ip = receivePack.getAddress().getHostAddress();
                    int port = receivePack.getPort();
                    int dataLen = receivePack.getLength();
                    String data = new String(receivePack.getData(), 0, dataLen);
                    System.out.println("UDPProvider receive from ip:" + ip + "\tport:" + port + "\tdata:" + data);

                    int responsePort = MessageCreator.parsePort(data);
                    if (responsePort != -1) {
                        String reponseData = MessageCreator.buildWithSn(sn);
                        byte[] reponseDataBytes = reponseData.getBytes();
                        DatagramPacket reponsePacket = new DatagramPacket(reponseDataBytes, reponseData.length(),
                                receivePack.getAddress(),
                                responsePort);
                        ds.send(reponsePacket);

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
            System.out.println("UDPProvider Finished");
        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        void exit() {
            done = true;
        }
    }
}
 
