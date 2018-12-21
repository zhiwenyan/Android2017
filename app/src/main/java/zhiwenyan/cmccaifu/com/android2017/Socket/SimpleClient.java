package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Description:
 * Data：11/7/2018-10:25 AM
 *
 * @author yanzhiwen
 */
public class SimpleClient {
    public static void main(String[] args) {
        try {
            System.out.println("-------客户端启动-------");
            //1.创建一个套接字并将其连接到指定的IP地址的指定端口
            Socket socket = new Socket("127.0.0.1", 8888);
            //2.获取套接字的输出流，用于输出数据
            OutputStream ops = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(ops);
            osw.write("你好!");
            osw.flush();
            osw.close();
            socket.close();
            System.out.println("客户端数据发送完毕!");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}