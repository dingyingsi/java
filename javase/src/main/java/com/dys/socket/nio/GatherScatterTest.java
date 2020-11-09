package com.dys.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;


/**
 * Scatter Read分散:将通道中的数据分散到多个缓冲区中
 * Gathering Write聚集：将多个缓冲区中的数据聚集到通道中
 * Scatter Read 可以将通道中的数据分散到多个缓冲区中，例如通道有数据A大小为12字节，可以在a缓冲中存放4字段，在b缓冲区存入8字节
 *
 * Gathering Write 可以将在a缓冲中存放4字段，在b缓冲区存入8字节，写入到A通道中
 */

public class GatherScatterTest {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);
        int messsage = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(3);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while(true) {
            int bytesRead = 0;
            while (bytesRead < messsage) {
                long r = socketChannel.read(buffers);
                bytesRead += r;

                System.out.println("byteRead: " + bytesRead);
                Arrays.asList(buffers).stream().map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit()).forEach(System.out::println);
            }
            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            long bytesWritten = 0;
            while(bytesWritten < messsage) {
                long r = socketChannel.write(buffers);
                bytesWritten += r;
            }
            Arrays.asList(buffers).forEach(buffer -> buffer.clear());
            System.out.println("bytesRead: " + bytesRead + ", bytesWrite: " + bytesWritten);
        }
    }
}
