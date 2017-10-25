package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple1;

/**
 * Created by yanzhiwen on 2017/10/25.
 * <p>
 * 单例设计  --》饿汉式
 */

public class Singleton {
    //随着类的加载已经new 了对象
    private static Singleton mInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return mInstance;
    }
}
