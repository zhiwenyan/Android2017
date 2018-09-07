package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

/**
 * Description:
 * Data：8/28/2018-2:45 PM
 *
 * @author yanzhiwen
 */
public class SocketUtil {
    public static void main(String[] args) {
        //   try {
//            InetAddress inetAddress;
//            Socket socket = new Socket("https://www.baidu.com/", 443);
//            inetAddress = socket.getInetAddress();
//            System.out.println("连接到 " + inetAddress);
//            socket.close();
//        } catch (java.io.IOException e) {
//            System.out.println("异常："+e.getCause());
//        }


        try {
            //代理服务器地址
            String proxyIP = "https://www.baidu.com/";
            int proxyPort = 443;//代理服务器端口
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIP, proxyPort));
            Socket socket = new Socket(proxy);
            //服务器的ip及地址
            socket.connect(new InetSocketAddress(proxyIP, proxyPort));
            System.out.println(socket.getKeepAlive());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
