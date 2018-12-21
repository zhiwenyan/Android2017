package zhiwenyan.cmccaifu.com.android2017.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description:
 * Data：11/7/2018-10:08 AM
 *
 * @author yanzhiwen
 */
public class WebSpider {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tmall.com/?spm=a2107.1.1000340.1.174c4265tShVTb");
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
