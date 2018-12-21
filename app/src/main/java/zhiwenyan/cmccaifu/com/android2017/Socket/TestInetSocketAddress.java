package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Description:
 * java.net.InetSocketAddress类:此类实现 IP 套接字地址（IP 地址 + 端口号）。
 * 构造方法
 * InetSocketAddress(InetAddress addr, int port)根据 IP 地址和端口号创建套接字地址。
 * InetSocketAddress(String hostname, int port) 根据主机名和端口号创建套接字地址。
 * 常用的方法:
 * getAddress():获取 InetAddress。
 * getPort() 获取端口号。
 * toString() 构造此 InetSocketAddress 的字符串表示形式。(主机名/Ip:端口号)
 * getHostName()获取 主机名。
 * Data：11/7/2018-10:04 AM
 *
 * @author yanzhiwen
 */
public class TestInetSocketAddress {
    public static void main(String[] args) {
//        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1",3306);
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 3306);
            System.out.println(socketAddress);
            InetAddress inetAddress = socketAddress.getAddress();
            System.out.println("主机信息:" + inetAddress);
            int port = socketAddress.getPort();
            System.out.println("端口号:" + port);
            String hostName = socketAddress.getHostName();
            System.out.println("主机名:" + hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}