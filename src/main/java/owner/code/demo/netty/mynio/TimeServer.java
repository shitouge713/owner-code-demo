package owner.code.demo.netty.mynio;

import java.io.IOException;

public class TimeServer {
    public static void main(String... args) throws IOException {
        int port = 8888;
        MultiplexerTimeServerHandler server = new MultiplexerTimeServerHandler(port);
        new Thread(server,"nio-MultiplexerTimeServerHandler-001").start();
    }
}
