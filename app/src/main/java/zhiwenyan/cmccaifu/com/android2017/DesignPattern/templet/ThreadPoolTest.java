package zhiwenyan.cmccaifu.com.android2017.DesignPattern.templet;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class ThreadPoolTest {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(4, Math.min(CPU_COUNT - 1, 5));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 2;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(9);

    //ArrayBlockingQueue 先进先出的队列
    //SynchronousQueue 线程安全的队列，它里面没有固定的缓存（OkHttp）
    //PriorityBlockingQueue 无序的可以根据优先级排序，指定对象是实现Comparable
    public static final Executor THREAD_POOL_EXECUTOR;
    //RejectedExecutionException 报错的原因，其实是AsyncTask一些隐患，比如去执行200个Runnable 肯定会报错

    //一共执行20个任务 ,核心线程数是4，最大核心线程数是10，目前加入的runnable20个(相当于20个任务），
    //20个任务需要执行，但是核心线程数只有4个，还有16个任务，由于LinkedBlockingDeque队列是最大存放的任务为10个，队列满了，则会创建新的线程去执行任务
    // 这个时候最大线程是10， 非核心线程数还有6个，这时候会开6个线程去执行，目前达到10个最大线程数，此时队列里面还有10个。正好满足队列的大小


    //一共执行20个任务 ,核心线程数是4，最大核心线程数是10，目前加入的runnable20个(相当于20个任务），
    //20个任务需要执行，但是核心线程数只有4个，还有16个任务，由于LinkedBlockingDeque队列是最大存放的任务为9个，队列满了，则会创建新的线程去执行任务
    //这个时候最大线程是10，非核心线程数还有6个，这时候会开6个线程去执行，目前达到10个最大线程数，此时队列里面最大只能存放9个，
    //还有一个Runnable，此时就会报错RejectedExecutionException

    static {
        System.out.println("核心线程数=" + CORE_POOL_SIZE);
        System.out.println("最大线程数=" + MAXIMUM_POOL_SIZE);

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
        for (int i = 0; i < 20; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("执行完毕" + Thread.currentThread().getName());
                }

            };
            //丢给线程池去执行
            THREAD_POOL_EXECUTOR.execute(runnable);
        }
    }
}
