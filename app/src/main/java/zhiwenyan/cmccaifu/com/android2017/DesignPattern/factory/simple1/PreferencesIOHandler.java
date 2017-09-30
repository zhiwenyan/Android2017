package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple1;

/**
 * Created by yanzhiwen on 2017/9/30.
 */

public class PreferencesIOHandler implements IOHandler {

    @Override
    public void save(String key, String value) {
        PreferencesUtils.getInstance().saveString(key, value);
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
        return  PreferencesUtils.getInstance().getString(key, "");
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
