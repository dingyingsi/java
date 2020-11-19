package com.dys.netty.n02.server;

import com.dys.netty.n02.decoder.CustomDecoder;
import com.dys.netty.n02.encoder.CustomEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new CustomDecoder());
        channelPipeline.addLast(new CustomEncoder());
        channelPipeline.addLast(new MyServerHandler());
    }
}
 
