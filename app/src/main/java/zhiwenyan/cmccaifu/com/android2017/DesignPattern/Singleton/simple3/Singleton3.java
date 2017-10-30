package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple3;

/**
 * Created by yanzhiwen on 2017/10/25.
 * <p>
 * <p>
 * 静态的内部类
 */

public class Singleton3 {
    private static Singleton3 mInstance;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return SingletonHolder.mInstance;
    }

    public static class SingletonHolder {
        private static volatile Singleton3 mInstance = new Singleton3();

    }

}
