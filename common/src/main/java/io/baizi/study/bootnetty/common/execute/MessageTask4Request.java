package io.baizi.study.bootnetty.common.execute;

import io.baizi.study.bootnetty.common.protobuf.MessageBuilder;
import io.baizi.study.bootnetty.common.protobuf.MessageModule;
import io.baizi.study.bootnetty.common.protobuf.Result;
import io.baizi.study.bootnetty.common.scanner.Invoker;
import io.baizi.study.bootnetty.common.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 10:46 下午
 */
@Slf4j
public class MessageTask4Request implements Runnable {
    private MessageModule.Message message;
    private ChannelHandlerContext channelHandlerContext;
    private static final String RETURN = "-return";

    public MessageTask4Request(MessageModule.Message message, ChannelHandlerContext channelHandlerContext) {
        this.message = message;
        this.channelHandlerContext = channelHandlerContext;
    }

    @Override
    public void run() {
        try {
            String module = message.getModule();
            String cmd = message.getCmd();
            byte[] data = message.getBody().toByteArray();
            Invoker invoker = InvokerTable.getInvoker(module, cmd);
            if (invoker == null) {
                return;
            }
            Result<?> result = (Result<?>) invoker.invoke(data);
            // 返回给调用方时，为了和服务端的方法区分开，使用module+RETURN方式进行区分。
            // 客户端异步调用服务端的方法，服务端执行完成后，调用客户端的方法进行结果处理。
            channelHandlerContext.writeAndFlush(MessageBuilder.buildResponseMessage(module + RETURN, cmd + RETURN,
                    result.getResponseType(),
                    result.getContent()));
        }finally {
            this.channelHandlerContext = null;
        }

    }
}
