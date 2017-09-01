package zhiwenyan.cmccaifu.com.android2017;

import android.app.Application;
import android.content.Context;

/**
 * 全局的Application
 */
public class CashMall extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
