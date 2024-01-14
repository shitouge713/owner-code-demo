package owner.code.demo.netty.mynetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class MyNettyServer {

    int port = 8888;

    public MyNettyServer(int port) {
        this.port = port;
    }
    public static void main(String[] args) {
        new MyNettyServer(8888).serverStart();
    }
    public void serverStart(){
        /**
         * 启动两个线程池
         * 一个负责接收请求
         * 一个负责具体的请求的逻辑处理
         */
        EventLoopGroup bossGroup  = new NioEventLoopGroup(3);
        EventLoopGroup workerGroup = new NioEventLoopGroup(5);
        /**
         * 配置类
         * server启动需要配置的信息
         * 配置两个线程池
         * 配置通道类型 NIO类型
         * 设置一个监听器，当有客户端连上时，触发监听器的操作
         * 监听器处理过程：在这个通道上加一个对这个通道的处理器，有连接进来时，执行处理器操作
         */
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new MyServerHandler());
                    }
                });
        try{
            ChannelFuture f = bootstrap.bind(8888).sync();
            f.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 处理器
     */
    class MyServerHandler extends ChannelInboundHandlerAdapter {
        /**
         * 读取客户端的数据
         *
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println(buf.toString(CharsetUtil.UTF_8));
            ctx.writeAndFlush(buf);
            ctx.close();
        }

        /**
         * 统一异常处理
         * 如果除了异常，关闭通道ctx
         * @param ctx
         * @throws Exception
         */
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.fireExceptionCaught(cause);
            ctx.close();
        }
    }
}


