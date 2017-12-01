package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple2.sync;

/**
 * Created by yanzhiwen on 2017/10/25.
 * 单例设计模式 ---懒汉式
 */

public class Singleton2 {

    //只有使用的时候去new
    //多线程的并发问题
    private static Singleton2 mInstance;

    private Singleton2() {
    }

    //双重枷锁 即保证线程的安全同是效率比较高的
    //这种方式还是有问题？
    public static Singleton2 getInstance() {
        if (mInstance == null) {
            synchronized (Singleton2.class) {
                if (mInstance == null) {
                    mInstance = new Singleton2();
                }
            }
        }
        return mInstance;
    }
}