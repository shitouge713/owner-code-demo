package owner.code.demo.netty.myrpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RPCClient {
    public static Object send(byte[] bs)  {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bs);
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[1024];
            in.read(buf);
            Object formatDate = SerializeUtils.doSerializer(buf);
            socket.close();
            return formatDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //main方法,运行客户端
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getInstance(HelloService.class);
        System.out.println("say:"+helloService.sayHello("zhangsan"));
        System.out.println("Person:"+helloService.getPerson("zhangsan"));

    }
}
