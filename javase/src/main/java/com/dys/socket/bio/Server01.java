package com.dys.socket.bio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Server01 {

    public static void main(String... args) {
        try {
            ServerSocketChannel ssChannel = ServerSocketChannel.open();
            ssChannel.bind(new InetSocketAddress("127.0.0.1", 9898));

            FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            SocketChannel sChannel = ssChannel.accept();

            ByteBuffer buf = ByteBuffer.allocate(1024);

            while (sChannel.read(buf) != -1) {
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }

            sChannel.close();
            ssChannel.close();
            outChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
