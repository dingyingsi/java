package com.dys.socket.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while(fisChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fosChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }
}
 
