package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2;

/**
 * Created by yanzhiwen on 2017/9/30.
 * <p>
 * 数据存储的一些规范
 */

public interface IOHandler {

    void save(String key, String value);

    void save(String key, double value);

    void save(String key, int value);

    void save(String key, long value);

    void save(String key, boolean value);

    void save(String key, Object value);


    String getString(String key);

    String getDouble(String key, double defaultValue);

    String getInt(String key, int defaultValue);

    String getLong(String key, int defaultValue);

    String getObject(String key, int defaultValue);

}
