package io.baizi.study.bootnetty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 7:52 下午
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
