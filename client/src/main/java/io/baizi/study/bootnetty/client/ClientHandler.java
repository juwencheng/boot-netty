package io.baizi.study.bootnetty.client;

import io.baizi.study.bootnetty.common.execute.MessageTask4Response;
import io.baizi.study.bootnetty.common.protobuf.MessageModule;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 7:52 下午
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    ThreadPoolExecutor workPool = new ThreadPoolExecutor(
            5, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4000), new ThreadPoolExecutor.DiscardPolicy()
    );
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageModule.Message message = (MessageModule.Message) msg;
        workPool.submit(new MessageTask4Response(message, ctx));
    }
}
