package zhiwenyan.cmccaifu.com.android2017;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.bumptech.glide.request.target.ViewTarget;

/**
 * 全局的Application
 */
public class BasicApp extends MultiDexApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ViewTarget.setTagId(R.id.glide_tag);


    }

    public static Context getContext() {
        return context;
    }

}
