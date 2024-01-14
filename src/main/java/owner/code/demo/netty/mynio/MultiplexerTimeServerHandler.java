package owner.code.demo.netty.mynio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO服务端
 * Selector
 * ServerSocketChannel
 * ByteBuffer
 */
public class MultiplexerTimeServerHandler implements Runnable {
    private Selector selector = null;
    private ServerSocketChannel servChannel = null;
    private int port;
    private boolean stop;
    public MultiplexerTimeServerHandler(int port) {
        try {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            //非阻塞
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            //在通道上注册selector,感兴趣事件是OP_ACCEPT
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器监听" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void stop() {
        this.stop = true;
    }
    @Override
    public void run() {
        while (!stop) {
            //循环监听就绪时间
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //多路复用器关闭后，所有注册的channel和pipe等资源都会自动去注册并关闭
        // ，所有不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //处理事件
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuff = ByteBuffer.allocate(1024);
                //上面设置了这里是非阻塞的
                int read = sc.read(readBuff);
                if (read > 0) {
                    readBuff.flip();
                    byte[] bytes = new byte[readBuff.remaining()];
                    readBuff.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("服务收到命令：" + body);
                    String currentTime = "time".equals(body) ? new Date(System.currentTimeMillis()).toString() : "无效命令";
                    doWrite(sc, currentTime);
                } else if (read < 0) {
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    //读到0字节
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String content) throws IOException {
        if (content != null && content.trim().length() > 0) {
            byte[] bytes = content.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            //可能存在写半包情况
            sc.write(byteBuffer);
        }
    }
}
