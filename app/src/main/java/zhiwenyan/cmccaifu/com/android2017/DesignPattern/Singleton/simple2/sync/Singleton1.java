package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple2.sync;

/**
 * Created by yanzhiwen on 2017/10/25.
 * 单例设计模式 ---懒汉式
 */

public class Singleton1 {

    //只有使用的时候去new
    //多线程的并发问题

    private static Singleton1 mInstance;

    private Singleton1() {
    }
    //虽然解决了线程的问题，但是又会出现效率的问题，会显得效率比较低，每次获取都需要经过同步锁的判断
    public static synchronized Singleton1 getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton1();
        }
        return mInstance;
    }
}