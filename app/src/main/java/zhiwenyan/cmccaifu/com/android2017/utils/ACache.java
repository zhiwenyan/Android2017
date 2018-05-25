package zhiwenyan.cmccaifu.com.android2017.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ACache {

    private static final String SP_NAME = "preference.ACACHE";
    private static final String EMPTY_STRING = "";

    private static final String KEY_USERNAME = "key.USERNAME";
    private static final String KEY_ID = "key.ID";

    private static volatile ACache instance;

    private SharedPreferences mSharedPreferences;

    public static ACache getInstance(Context context) {
        if (instance == null) {
            synchronized (ACache.class) {
                if (instance == null) {
                    instance = new ACache(context);
                }
            }
        }
        return instance;
    }

    private ACache(Context context) {
        mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存 username
     */
    public void saveUsername(String username) {
        mSharedPreferences.edit().putString(KEY_USERNAME, username).apply();
    }

    /**
     * 获取 username
     */
    public String getUsername() {
        return mSharedPreferences.getString(KEY_USERNAME, EMPTY_STRING);
    }

    /**
     * 保存 id
     */
    public void saveId(String id) {
        mSharedPreferences.edit().putString(KEY_ID, id).apply();
    }

    /**
     * 获取 id
     */
    public String getId() {
       return mSharedPreferences.getString(KEY_ID, EMPTY_STRING);
    }

    public void removeAll() {
        mSharedPreferences.edit().clear().commit();
        // .remove(username)
        // .apply();

    }
}