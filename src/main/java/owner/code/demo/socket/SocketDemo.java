package owner.code.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("localhost"));
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            socket = serverSocket.accept();//1.监听到客户端的连接
            is = socket.getInputStream();
            os = socket.getOutputStream();
            //Request request = Util.getRequest(is);//2.从输入流中读取数据，并根据Http协议转换成请求
            //Response response = Util.service(request);//服务器内部根据请求信息给出响应信息
            //os.writeResponse(response);//3.将响应信息写到输出流
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.关闭输入输出流及连接
            if (is != null) is.close();
            if (os != null) os.close();
            socket.close();
        }
    }
}
