package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Description:
 * Data：4/24/2018-11:51 AM
 *
 * @author yanzhiwen
 */
public class Count {
    private  int countNum = 0;

    //ThreadLocal的作用是提供线程内的局部变量，这种变量在线程的生命周期内起作用，
    //减少同一个线程内多个函数或者组件之间一些公共变量的传递的复杂度。
//    private ThreadLocal<Integer> mThreadLocal = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };

    public void count() {
        for (int i = 1; i <= 100; i++) {
            countNum = countNum + 10;
//          mThreadLocal.set(mThreadLocal.get() + i);
        }
        System.out.println(Thread.currentThread().getName() + "-" + countNum);
    }

    public static void main(String[] args) {
        java.lang.Runnable runnable = new java.lang.Runnable() {
            Count count = new Count();

            public void run() {
                count.count();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }
    }
}