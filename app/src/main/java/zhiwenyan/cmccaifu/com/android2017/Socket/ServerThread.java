package zhiwenyan.cmccaifu.com.android2017.Socket;

/**
 * Description:
 * Data：11/7/2018-10:32 AM
 *
 * @author yanzhiwen
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于处理客户端请求的线程
 * @author Administrator
 *
 */
public class ServerThread extends Thread{
    private Socket socket;
    //用于保存用户名和密码的信息，利用用户名做键，利用密码做值
    static Map<String,String> map = new HashMap<String,String>();
    //初始化map
    static{
        map.put("zzsxt", "zzsxt");
        map.put("bjsxt", "bjsxt");
        map.put("whsxt", "whsxt");
    }
    static int count=0;//第几位访客
    //构造方法
    public ServerThread(Socket socket){
        this.socket=socket;
    }
    /**
     * 处理客户端请求
     */
    @Override
    public void run() {
        ObjectInputStream ois =null;
        DataOutputStream dos=null;
        try {
            InputStream ips = socket.getInputStream();
            ois = new ObjectInputStream(ips);
            User user = (User)ois.readObject();
            //2.判断用户名和密码是否正确
            String userName = user.getUsername();//获取用户输入的用户名 aa
            String password = user.getPassword();//获取用户输入的密码
            String upass=map.get(userName);
            String message;
            if(upass!=null&&upass.equals(password)){
                count++;//计数
                message="恭喜你,登陆成功!您是第"+count+"位访客";
            }else{
                message="用户名或密码有误!";
            }
            //3.创建输出流向客户端发送消息
            OutputStream ops = socket.getOutputStream();
            dos = new DataOutputStream(ops);
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                ois.close();
                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}