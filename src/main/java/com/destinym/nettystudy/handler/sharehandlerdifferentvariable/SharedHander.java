package com.destinym.nettystudy.handler.sharehandlerdifferentvariable;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Created by destinym on 15/11/23.
 */


@ChannelHandler.Sharable
public class SharedHander extends ChannelInboundHandlerAdapter {



    private static final InternalLogger logger = InternalLoggerFactory.getInstance(SharedHander.class);
    private final AttributeKey<String> clientKey =
            AttributeKey.valueOf("client");
    private String client;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        Attribute<String> attr = ctx.attr(clientKey);
        String s = (String)msg;
        if (s.contains("firstMessage")) {
            client = s;
            attr.set(s);

        } else {
            logger.info("client-private:   " + client + "\n receive:  " + s);
            logger.info("client-key:  " + attr.get()+" \n receive :" + s);

        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    //@Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

//        System.out.println("s=="+msg);
//        logger.info("receive===="+msg);
//        logger.info("receive" +ctx.channel());
//        Attribute<String> attr = ctx.attr(clientKey);
//        //Channel ch = ctx.channel();
//
//        String s = (String)msg;
//        if (s.contains("firstMessage")) {
//            client = s;
//            attr.set(s);
//
//        } else {
//            logger.info("client" + client + "   receive:"+ s);
//            logger.info("client-key:" + attr.get()+" receive:"+s);
//
//        }

    }
}
