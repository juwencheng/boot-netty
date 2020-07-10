package io.baizi.study.bootnetty.common.execute;

import io.baizi.study.bootnetty.common.protobuf.MessageBuilder;
import io.baizi.study.bootnetty.common.protobuf.MessageModule;
import io.baizi.study.bootnetty.common.protobuf.Result;
import io.baizi.study.bootnetty.common.scanner.Invoker;
import io.baizi.study.bootnetty.common.scanner.InvokerTable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import sun.jvm.hotspot.debugger.Page;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 10:46 下午
 */
@Slf4j
public class MessageTask4Response implements Runnable {
    private MessageModule.Message message;
    private ChannelHandlerContext channelHandlerContext;

    public MessageTask4Response(MessageModule.Message message, ChannelHandlerContext channelHandlerContext) {
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
            // 返回
            channelHandlerContext.writeAndFlush(MessageBuilder.buildResponseMessage(module, cmd, result.getResponseType(),
                    result.getContent()));
        }finally {
            // NOTE 一定要释放资源
            ReferenceCountUtil.release(message);
            this.channelHandlerContext = null;
        }

    }
}
