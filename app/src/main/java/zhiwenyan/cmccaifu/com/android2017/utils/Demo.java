package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Description:
 * Data：11/17/2017-9:42 AM
 * Author: yanzhiwen
 */

interface Runnable {
    void run();
}

public class Demo implements java.lang.Runnable {
    private Runnable mRunnable;

    public Demo() {

    }

    public Demo(Runnable runnable) {
        mRunnable = runnable;
        mRunnable.run();

    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }

    @Override
    public void run() {
        System.out.println("-------");
    }
}
