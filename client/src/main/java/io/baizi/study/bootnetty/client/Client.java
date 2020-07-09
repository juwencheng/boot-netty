package io.baizi.study.bootnetty.client;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 7:42 下午
 */
@Component
public class Client {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private EventLoopGroup group = new NioEventLoopGroup(2);

    public static final String HOST = "127.0.0.1";

    public static final int PORT = 8888;

    public static final String VIP_HOST = "192.168.11.90";

    public static final int VIP_PORT = 8888;


    private Channel channel;

    private AtomicBoolean isConnect = new AtomicBoolean(false);

    private static class SingletonHolder {
        private static final Client INSTANCE = new Client();
    }

    public static final Client getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Client() {
    }

    public synchronized void init() {
        if (!isConnect.get()) {
            try {
                this.connect(VIP_HOST, VIP_PORT);
                isConnect.set(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void connect(final String host, final int port) throws Exception {

        // 配置客户端NIO线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {

                        }
                    });
            // 发起异步连接操作
            ChannelFuture future = b.connect(host, port).sync();
            this.channel = future.channel();
            System.out.println("Client Start.. ");
            this.channel.closeFuture().sync();
        } finally {
            // 	所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            connect(host, port);// 发起重连操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * $sendMessage
     * 发送数据的方法
     *
     * @param module      模块
     * @param cmd         指令
     * @param messageData 数据内容
     */
    public void sendMessage(String module, String cmd, GeneratedMessageV3 messageData) {
//        this.channel.writeAndFlush(MessageBuilder.getRequestMessage(module, cmd, messageData));
    }
}
