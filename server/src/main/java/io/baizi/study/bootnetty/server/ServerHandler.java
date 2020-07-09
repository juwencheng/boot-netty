package io.baizi.study.bootnetty.server;

import io.baizi.study.bootnetty.common.execute.MessageTask4Request;
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
public class ServerHandler extends ChannelInboundHandlerAdapter {
    ThreadPoolExecutor workPool = new ThreadPoolExecutor(
            5, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4000), new ThreadPoolExecutor.DiscardPolicy()
    );

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageModule.Message request = (MessageModule.Message) msg;
        // 建议把request仍到异步线程池中
        workPool.submit(new MessageTask4Request(request, ctx));
    }
}
