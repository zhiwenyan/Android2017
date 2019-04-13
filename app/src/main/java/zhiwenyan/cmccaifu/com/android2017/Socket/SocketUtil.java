package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.charset.Charset;

import okio.BufferedSource;
import okio.Okio;
import okio.Source;

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
            String path = "https://www.baidu.com";
            URL url = new URL(path);
            System.out.println("port=" + url.getDefaultPort());
            SocketAddress socketAddress = new InetSocketAddress(url.getHost(), url.getDefaultPort());
            System.out.println("连接到 " + ((InetSocketAddress) socketAddress).getAddress().getCanonicalHostName());
            Socket socket = new Socket();
            socket.connect(socketAddress);
            readByOkio(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("异常：" + e.getMessage());
        }


//
//        Socket socket = new Socket();
//        SocketAddress socketAddr = new InetSocketAddress("www.tmall.com", 443);
//        try {
//            socket.connect(socketAddr);
//            System.out.println(socket.getKeepAlive());
//            System.out.println(socket.getInputStream().read());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //构造代理服务器地址
//        SocketAddress sa = new InetSocketAddress("192.168.10.92", 808);
//        //构造Socket代理
//        Proxy proxy = new Proxy(Proxy.Type.SOCKS, sa);
//        //使用代理创建socket
//        Socket socket = new Socket(proxy);
//        //构造目标地址
//        SocketAddress socketAddr = new InetSocketAddress("www.baidu.com", 80);
//        //socket使用代理连接目标地址
//        try {
//            socket.connect(socketAddr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void read(InputStream inputStream) {
        BufferedInputStream stream = new BufferedInputStream(inputStream);
        int length = 0;
        byte[] bytes = new byte[4096];
        try {
            while ((length = stream.read()) != -1) {
                //将读取的字节转为字符串对象
                String chunk = new String(bytes, 0, length);
                System.out.print(chunk);
            }
            stream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readByOkio(InputStream inputStream) {
        BufferedSource bufferedSource = null;
        Source source = Okio.source(inputStream);
        bufferedSource = Okio.buffer(source);
        String read = null;
        try {
            read = bufferedSource.readString(Charset.forName("GBK"));
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedSource.close();
                source.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
