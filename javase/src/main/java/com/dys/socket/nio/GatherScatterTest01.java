package com.dys.socket.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GatherScatterTest01 {
    public static void main(String[] args) throws Exception {

        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
        FileChannel channel1 = raf1.getChannel();
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        ByteBuffer[] bufs = new ByteBuffer[]{buf1, buf2};

        // Scatter read
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("-----------------------------------------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
 
        // Gather write
        channel2.write(bufs);

        channel2.close();
        channel1.close();

        raf2.close();
        raf1.close();
    }
}
