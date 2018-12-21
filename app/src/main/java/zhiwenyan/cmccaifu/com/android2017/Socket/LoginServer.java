package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * <p>
 * 服务器端:
 * 1.获取客户端发送的user对象(封装了用户名和密码)
 * 2.判断用户名和密码是否合法
 * 3.如果合法,向客户端发送"恭喜你，登陆成功!";否则向客户端发送"用户名或密码有误!"
 * 4.释放资源
 * <p>
 * Data：11/7/2018-10:28 AM
 *
 * @author yanzhiwen
 */
/**
 * 面临的问题:
 *     当多个用户同时登陆时，只能排队等待。
 * 解决方案:使用多线程进行解决，为每一个客户请求创建线程，为其提供服务。
 *
 * 服务器端:
 * 1.获取客户端发送的user对象(封装了用户名和密码)
 * 2.判断用户名和密码是否合法
 * 3.如果合法,向客户端发送"恭喜你，登陆成功!";否则向客户端发送"用户名或密码有误!"
 * 4.释放资源
 *
 */
public class LoginServer {

    public static void main(String[] args) {
        //1.获取客户端发送的用户名和密码信息
        ServerSocket serverSocket=null;
        try {
            serverSocket = new ServerSocket(6666);
            while(true){
                Socket socket = serverSocket.accept();
                //启动线程，处理用户请求
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}