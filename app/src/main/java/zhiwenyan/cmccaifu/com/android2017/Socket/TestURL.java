package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description:
 * URL:统一资源定位符
 * 组成部分:协议,主机名或IP,端口号,资源路径
 * java.net.URL类:代表一个统一资源定位符，它是指向互联网“资源”的指针
 * 常用的构造方法
 * URL(String spec) 根据 String 表示形式创建 URL 对象。
 * URL(String protocol, String host, int port, String file) 根据指定 protocol、host、port 号和 file 创建 URL 对象。
 * 常用的方法:
 * getProtocol()获取此 URL 的协议名称。
 * getHost()获取此 URL 的主机名（如果适用）。
 * getPort() 获取此 URL 的端口号。
 * getFile()获取此 URL 的文件名。
 * getDefaultPort()获取与此 URL 关联协议的默认端口号。
 * getPath()获取此 URL 的路径部分。
 * Data：11/7/2018-10:06 AM
 *
 * @author yanzhiwen
 */
public class TestURL {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.baidu.com/");
            String protocol = url.getProtocol();
            System.out.println("协议:" + protocol);
            String host = url.getHost();
            System.out.println("主机名:" + host);
            int port = url.getPort();
            System.out.println("端口号:" + port);
            int defualtPort = url.getDefaultPort();
            System.out.println("默认端口:" + defualtPort);
            String file = url.getFile();
            System.out.println("资源路径:" + file);
            String path = url.getPath();
            System.out.println("资源路径:" + path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}