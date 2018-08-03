package zhiwenyan.cmccaifu.com.android2017.DesignPattern.templet;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class ThreadPoolTest {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(3, Math.min(CPU_COUNT - 1, 5));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new SynchronousQueue<>();

    //BlockingQueue 先进先出的队列
    //SynchronousQueue 线程安全的队列，它里面没有固定的缓存（OkHttp）
    //PriorityBlockingQueue 无序的可以根据优先级排序，指定对象是实现Comparable

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    public static final Executor THREAD_POOL_EXECUTOR;
    //RejectedExecutionException 报错的原因，其实是AsyncTask一些隐患，比如去执行200个Runnable 肯定会报错


    //线程队列4 ,核心线程数也是4，最大核心线程数是10，目前加入的runnable20个，
    //20个任务需要执行，但是核心线程数只有4个，还有16个任务对列，这个时候最大线程是10， 非核心线程数还有6个，这时候会开6个线程去执行，
    //目前达到10个最大线程数，还有10个Runnable没有办法执行，则会抛出异常
    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,  //核心线程数
                MAXIMUM_POOL_SIZE, //线程池中最大的线程数
                60,  //线程的存活时间，没事干的时候，空闲的时间
                TimeUnit.SECONDS, //线程存活时间的单位
                sPoolWorkQueue, //线程缓存队列
                new ThreadFactory() {  //线程创建工厂，如果线程池需要创建线程会调用newThread来创建
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(false);
                        return thread;
                    }
                });
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;

    }

    public static void main(String[] args) {
        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i < 5; i++) {
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
                THREAD_POOL_EXECUTOR.execute(runnable);
            }
        }
    }
}
