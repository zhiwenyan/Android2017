package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * Data：2018/7/29
 * Author:Steven
 */
public class EchoServer {
    private final ServerSocket mServerSocket;

    public EchoServer(int port) throws IOException {
        mServerSocket = new ServerSocket(port);
    }

    public void run() throws IOException {
        // 2. 开始接受客户连接
        Socket client = mServerSocket.accept();
        handleClient(client);
    }

    private void handleClient(Socket socket) throws IOException {
        // 3. 使用 socket 进行通信
        InputStream in = socket.getInputStream();

        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = in.read(buffer)) > 0) {
            System.out.write(buffer,0,n);
            out.write(buffer, 0, n);
        }

    }

    public static void main(String[] args) {
        try {
            EchoServer server = new EchoServer(9877);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
