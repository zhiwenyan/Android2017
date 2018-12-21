package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * Data：11/7/2018-10:24 AM
 *
 * @author yanzhiwen
 */
public class SimpleServer {
    public static void main(String[] args) {
        try {
            System.out.println("------服务器端启动------");
            //1.创建服务器端的套接字并指定端口
            ServerSocket serverSocket = new ServerSocket(8888);
            //2.侦听并接受到此套接字的连接。
            Socket socket = serverSocket.accept();
            //3.从套接字中获取一个输入流
            InputStream ips = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(ips);
            char[] cs = new char[1024];
            int len = isr.read(cs);
            String message = new String(cs, 0, len);
            System.out.println("客户端消息:" + message);
            isr.close();
            socket.close();
            serverSocket.close();
            System.out.println("服务器数据接收完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}