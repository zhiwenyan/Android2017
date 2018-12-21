package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description:IP地址:在网络上唯一标示一台计算机
 * 端口号:标示计算机上不同的应用程序
 * java.net.InetAddress类:此类表示互联网协议 (IP) 地址。
 * 常用方法:
 * getByName(String host) 在给定主机名的情况下确定主机的 IP 地址。
 * getHostName() 获取此 IP地址的主机名。
 * getHostAddress()返回 IP 地址字符串（以文本表现形式）。
 * getLocalHost() 返回本地主机。
 * getAllByName(String host) 在给定主机名的情况下，根据系统上配置的名称服务返回其 IP 地址所组成的数组。
 * <p>
 * Data：11/7/2018-10:02 AM
 *
 * @author yanzhiwen
 */
public class TestInetAddress {
    public static void main(String[] args) {
        try {
            //在给定主机名的情况下确定主机的 IP 地址。
//          InetAddress inetAddress = InetAddress.getByName("P-PC");
            InetAddress inetAddress = InetAddress.getLocalHost();//获取本地主机
            System.out.println(inetAddress);
            String hostName = inetAddress.getHostName();
            System.out.println("主机名:" + hostName);
            String ip = inetAddress.getHostAddress();
            System.out.println("IP地址:" + ip);
            //根据主机名或域名获取其IP地址
            InetAddress[] ids = InetAddress.getAllByName("www.baidu.com");
            for (InetAddress inetAddress2 : ids) {
                System.out.println(inetAddress2);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
