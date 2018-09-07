package zhiwenyan.cmccaifu.com.android2017.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Description:
 * Data：8/28/2018-2:01 PM
 *
 * @author yanzhiwen
 */
public class FileUtil {
    public static void read() {
        try {
            PrintWriter printWriter = new PrintWriter(System.out);
            OutputStream outputStream = new FileOutputStream("C:\\Users\\fumi_it1\\Desktop\\test1.txt");
            InputStream inputStream = new FileInputStream("C:\\Users\\fumi_it1\\Desktop\\test.txt");
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                printWriter.print(new String(bytes, "GBK"));
            }
            // System.out.println(bufferedReader.readLine());
            printWriter.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        read();
    }
}
