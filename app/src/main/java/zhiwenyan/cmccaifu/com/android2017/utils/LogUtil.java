package zhiwenyan.cmccaifu.com.android2017.utils;

import android.util.Log;



public class LogUtil {

    public static final String TAG = "Android2017";

    public static void d(String msg) {
        if (Deployment.logEnabled)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (Deployment.logEnabled)
            Log.e(TAG, msg);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void p(String msg) {
        if (Deployment.logEnabled)
            System.out.print("====" + msg);
    }
}
