package com.dys.netty.n02.encoder;

import com.dys.netty.n02.protocol.PersonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CustomEncoder extends MessageToByteEncoder<PersonProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("CustomEncoder invoked");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
