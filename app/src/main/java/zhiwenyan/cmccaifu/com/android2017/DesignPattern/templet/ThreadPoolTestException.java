package zhiwenyan.cmccaifu.com.android2017.DesignPattern.templet;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class ThreadPoolTestException {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<>(6);

    //BlockingQueue 先进先出的队列
    //SynchronousQueue 线程安全的队列，它里面没有固定的缓存（OkHttp）
    //PriorityBlockingQueue 无序的可以根据优先级排序

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    public static final Executor THREAD_POOL_EXECUTOR;
    //RejectedExecutionException 报错的原因，其实是AsyncTask一些隐患，比如去执行200个Runnable 肯定会报错


    //线程队列4 最大核心线程数是10 缓存队列是6 20个Runnable
    //20个任务需要执行，但是核心线程数只有4个，还有14个任务对列 非核心线程数还有6个，这时候会开6个线程去执行，
    //目前达到10个最大线程数，还有8个Runnable没有办法执行，则会抛出异常
    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,  //核心线程数
                10, //线程池中最大的线程数
                60,  //线程的存活时间，没事干的时候，空闲的时间
                TimeUnit.SECONDS, //线程存活时间的单位
                sPoolWorkQueue, //线程缓存队列
                new ThreadFactory() {  //线程创建工厂，如果线程池需要创建线程会调用newThread来创建
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(false);  //不是守护线程
                        return thread;
                    }
                });
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;


        for (int i = 0; i < 20; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("下载图片显示完毕" + Thread.currentThread().getName());
                }
            };
            //加入线程队列
            threadPoolExecutor.execute(runnable);
        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("下载图片显示完毕");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }


    }
}