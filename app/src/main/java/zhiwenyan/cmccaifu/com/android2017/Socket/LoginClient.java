package zhiwenyan.cmccaifu.com.android2017.Socket;

/**
 * Description:
 * Data：11/7/2018-10:27 AM
 *
 * @author yanzhiwen
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * 客户端:
 * 1.获取用户名和密码
 * 2.将用户名和密码封装成User对象
 * 3.使用对象流将user对象发生到服务器端
 * 4.读取服务器的响应消息
 * 5.释放资源
 */
public class LoginClient {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //获取输入的用户名和密码
        System.out.println("请输入用户名:");
        String userName = input.next();
        System.out.println("请输入密码:");
        String password = input.next();
        //将用户名和密码封装成User对象
        User user = new User(userName,password);
        try {
            //创建Socket对象
            Socket socket = new Socket("127.0.0.1",6666);
            //获取输出流
            OutputStream ops = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(ops);
            //使用对象流将user对象发送到服务器端
            oos.writeObject(user);
            oos.flush();
            //获取服务器端响应消息
            InputStream ips = socket.getInputStream();
            DataInputStream dis = new DataInputStream(ips);
            String str = dis.readUTF();
            System.out.println(str);
            //释放资源
            dis.close();
            oos.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}