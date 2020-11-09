package com.dys.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client03 {
    public static void main(String... args) throws Exception {

        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        sChannel.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String str = scanner.next();
            buf.put((str + LocalDateTime.now().toString()).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.close();
    }
}
