package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

/**
 * Description:
 * Data：8/28/2018-2:45 PM
 *
 * @author yanzhiwen
 */
public class SocketUtil {

    public static void main(String[] args) {
//
//        try {
//            //在给定主机名的情况下确定主机的 IP 地址。
////            InetAddress inetAddress = InetAddress.getByName("P-PC");
//            InetAddress inetAddress = InetAddress.getLocalHost();//获取本地主机
//            System.out.println(inetAddress);
//            String hostName = inetAddress.getHostName();
//            System.out.println("主机名:"+hostName);
//            String ip = inetAddress.getHostAddress();
//            System.out.println("IP地址:"+ip);
//            //根据主机名或域名获取其IP地址
//            InetAddress[] ids = InetAddress.getAllByName("www.baidu.com");
//            for (InetAddress inetAddress2 : ids) {
//                System.out.println(inetAddress2);
//            }
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

        try {
            String path = "https://www.tmall.com/";
            URL url = new URL(path);
            System.out.println("port=" + url.getDefaultPort());
            SocketAddress socketAddress = new InetSocketAddress(url.getPath(), url.getDefaultPort());
            System.out.println("连接到 " + socketAddress);
            Socket socket = new Socket();
            socket.connect(socketAddress);
            System.out.println();
        } catch (IOException e) {
            System.out.println("异常：" + e.getMessage());
        }


        try {
            //代理服务器地址
            String path = "https://www.tmall.com/";
            int proxyPort = 443;//代理服务器端口
//            InetSocketAddress socketAddress = new InetSocketAddress(path, proxyPort);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, socketAddress);
            Socket socket = new Socket(path,proxyPort);
            //服务器的ip及地址
            System.out.println(socket.getKeepAlive());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
