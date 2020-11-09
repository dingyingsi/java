package com.dys.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Server {

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

            buf.put("服务端面接受数据成功".getBytes());
            buf.flip();
            sChannel.write(buf);

            sChannel.close();
            ssChannel.close();
            outChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 
