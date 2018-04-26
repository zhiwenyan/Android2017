package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple2;

/**
 * Created by yanzhiwen on 2017/10/25.
 * 单例设计模式 ---懒汉式
 */

public class Singleton {

    //只有使用的时候去new
    //多线程的并发问题

    private static Singleton mInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
    }
}