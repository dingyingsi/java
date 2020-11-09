package com.dys.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 编码：字符串 -> 字节数组
 * 解码:  字节数组 -> 字符串
 */
public class CharsetTest01 {
    public static void main(String[] args) throws Exception {

        Charset cs1 = Charset.forName("GBK");

        CharsetEncoder ce = cs1.newEncoder();
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);

        cBuf.put("尚硅谷威武！");
        cBuf.flip();

        // 编码
        ByteBuffer bBuf = ce.encode(cBuf);
        for (int i = 0; i < bBuf.limit(); i++) {
            System.out.println(bBuf.get());
        }

        bBuf.flip();
        // 解码
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("------------------------------");

        Charset cs2 = Charset.forName("UTF-8");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }
}
