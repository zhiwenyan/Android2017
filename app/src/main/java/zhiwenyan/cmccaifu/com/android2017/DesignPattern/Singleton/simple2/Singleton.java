package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple2;

/**
 * Created by yanzhiwen on 2017/10/25.
 * 单例设计模式 ---懒汉式
 */

public class Singleton {

    private static Singleton s1, s2;
    //只有使用的时候去new
    //多线程的并发问题

    private static Singleton mInstance;

    private Singleton() {
    }

    public  static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public static void main(String[] args) {
         s1 = Singleton.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                s2 = Singleton.getInstance();
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s1==s2);

            }
        }).start();

    }
}