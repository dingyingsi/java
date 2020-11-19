package com.dys.netty.n02.server;

import com.dys.netty.n02.protocol.PersonProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("server received data : ");
        System.out.println("length : " + length);
        System.out.println("content: " + new String(content, Charset.forName("UTF-8")));
        System.out.println("count : " + (++count));

        String responseMessage = UUID.randomUUID().toString();
        int responseLength = responseMessage.getBytes("UTF-8").length;
        byte[] responseContent = responseMessage.getBytes("UTF-8");
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(responseLength);
        personProtocol.setContent(responseContent);

        ctx.writeAndFlush(personProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
 
