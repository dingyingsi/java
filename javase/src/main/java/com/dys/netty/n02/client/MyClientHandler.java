package com.dys.netty.n02.client;

import com.dys.netty.n02.protocol.PersonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;


/**
 * 出站入站处理器
 * 1.	Netty的处理器可以为分两类：入站处理器和出站处理器；
 * 2.	入站处理器的顶层是ChannelInboundHandler， 出站处理器的顶层是ChannelOutboundHandler;
 * 3.	数据处理时常用的各种编解码器本质上都是处理器；
 * 4.	编解码器：无论我们向网络中写入的数据是什么类型，数据在网络中传递时，其都以字节流的形式呈现；数据由原来的形式转换为字节流的操作称为编码，将数据字节转换为它原本的格式的操作称为解码，编解码统一称为codec;
 * 5.	编码：本质上是一种出站处理器；因此，编码一定是一种ChannelOutbountHandler;
 * 6.	解码：本质上是一种入站处理器；因此，编码一定是一种ChannelInboundHandler;
 * 7.	在Netty中，编码器通常以XXXEncoder命名；解码器通常以XXXDecoder命名；
 */

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String messageToBeSent = "sent from client";
            byte[] content = messageToBeSent.getBytes("UTF-8");
            int length = content.length;

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);

            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("client receive message: ");
        System.out.println("length: " + length);
        System.out.println("content: " + new String(content, Charset.forName("UTF-8")));

        System.out.println("client receive count: " + (++count));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Echo Client read complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
