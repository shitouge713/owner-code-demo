package owner.code.demo.netty.mynetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.channel.socket.SocketChannel;

public class MyNettyClient {
    private final String host;
    private final int port;
    public MyNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new MyNettyClient("127.0.0.1",8888).start();
    }
    public void start() throws InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .remoteAddress("127.0.0.1",8888)
                    .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("channel initializer");
                        socketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect().sync();
        future.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
    class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            System.out.println("Client received: "+byteBuf.toString(CharsetUtil.UTF_8));
        }

        /**
         * 通道建立，写入消息
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.copiedBuffer("netty rocks",CharsetUtil.UTF_8));
        }
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf message = (ByteBuf)msg;
            System.out.println(message.toString());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
