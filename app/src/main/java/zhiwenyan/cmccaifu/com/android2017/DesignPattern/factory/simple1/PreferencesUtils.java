package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yanzhiwen on 2017/9/30.
 */

public class PreferencesUtils {
    private static volatile PreferencesUtils mInstance;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private PreferencesUtils() {

    }

    public static PreferencesUtils getInstance() {
        if (mInstance == null) {
            synchronized (PreferencesUtils.class) {
                if (mInstance == null) {
                    mInstance = new PreferencesUtils();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        mPreferences = context.getApplicationContext().getSharedPreferences("cache", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public PreferencesUtils saveString(String key, String value) {
        mEditor.putString(key, value).commit();
        return this;
    }

    public PreferencesUtils commit() {
        mEditor.commit();
        return this;
    }

    public String getString(String key, String defaultValue) {
        return mPreferences.getString(key, defaultValue);
    }

}
