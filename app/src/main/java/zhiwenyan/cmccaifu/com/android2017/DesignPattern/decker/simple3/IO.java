package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple3;

        import java.io.BufferedInputStream;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileReader;

/**
 * Created by yanzhiwen on 2017/11/6.
 */

public class IO {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("xxx.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            FileReader fileReader = new FileReader("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
