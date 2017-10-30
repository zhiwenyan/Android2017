package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple2.sync;

/**
 * Created by yanzhiwen on 2017/10/25.
 * 单例设计模式 ---懒汉式
 */

public class Singleton3 {

    //只有使用的时候去new
    //多线程的并发问题
    //加上volatile关键字
    private static volatile Singleton3 mInstance;

    private Singleton3() {}

    //双重枷锁 即保证线程的安全同是效率比较高的
    //这种方式还是有问题？
    public static Singleton3 getInstance() {
        if (mInstance == null) {
            synchronized (Singleton3.class) {
                if (mInstance == null) {
                    mInstance = new Singleton3();
                }
            }
        }
        return mInstance;
    }
}