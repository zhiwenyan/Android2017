package zhiwenyan.cmccaifu.com.android2017.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

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


    public static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        for (String permission : permissions) {
            String op = AppOpsManagerCompat.permissionToOp(permission);
            if (TextUtils.isEmpty(op)) continue;
            int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
            if (result == AppOpsManagerCompat.MODE_IGNORED) return false;
            result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        read();
    }
}
