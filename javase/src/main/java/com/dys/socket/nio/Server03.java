package com.dys.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server03 {

    public static void main(String... args) throws Exception {

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.bind(new InetSocketAddress("127.0.0.1", 9898));

        Selector selector = Selector.open();
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel sChannel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }

                iterator.remove();
            }
        }
    }
}
