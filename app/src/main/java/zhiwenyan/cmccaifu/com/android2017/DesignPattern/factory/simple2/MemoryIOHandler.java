package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2;

import android.util.LruCache;

/**
 * Created by yanzhiwen on 2017/9/30.
 * <p>
 * 磁盘缓存
 */

public class MemoryIOHandler implements IOHandler {

    //存在运行内存中
    private static LruCache<String, Object> mCache = new LruCache<>(10 * 1024 * 1024);  //最好是运行内存的八分之一

    @Override
    public void save(String key, String value) {
        mCache.put(key, value);
    }

    @Override
    public void save(String key, double value) {

    }

    @Override
    public void save(String key, int value) {

    }

    @Override
    public void save(String key, long value) {

    }

    @Override
    public void save(String key, boolean value) {

    }

    @Override
    public void save(String key, Object value) {

    }

    @Override
    public String getString(String key) {
        return (String) mCache.get(key);
    }

    @Override
    public String getDouble(String key, double defaultValue) {
        return null;
    }

    @Override
    public String getInt(String key, int defaultValue) {
        return null;
    }

    @Override
    public String getLong(String key, int defaultValue) {
        return null;
    }

    @Override
    public String getObject(String key, int defaultValue) {
        return null;
    }
}
