package zhiwenyan.cmccaifu.com.android2017;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.request.target.ViewTarget;

/**
 * 全局的Application
 */
public class App extends Application {

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
