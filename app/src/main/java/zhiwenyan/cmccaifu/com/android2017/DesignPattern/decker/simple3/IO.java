package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yanzhiwen on 2017/11/6.
 */

public class IO {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("xxx.txt");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            FileReader fileReader = new FileReader("");
            fileReader.read();
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            bufferedReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
