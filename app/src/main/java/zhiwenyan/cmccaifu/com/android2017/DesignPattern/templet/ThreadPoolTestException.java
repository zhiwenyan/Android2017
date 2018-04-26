package zhiwenyan.cmccaifu.com.android2017.DesignPattern.templet;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
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
    private static final int CORE_POOL_SIZE = Math.max(3, Math.min(CPU_COUNT - 1, 5));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new SynchronousQueue<>();

    //BlockingQueue 先进先出的队列
    //SynchronousQueue 线程安全的队列，它里面没有固定的缓存（OkHttp）
    //PriorityBlockingQueue 无序的可以根据优先级排序，指定对象是实现Comparable

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    public  ExecutorService mExecutorService;
    //RejectedExecutionException 报错的原因，其实是AsyncTask一些隐患，比如去执行200个Runnable 肯定会报错

    /**
     * 创建线程池
     *
     * @return mExecutorService
     */
    private synchronized ExecutorService executorService() {
        if (mExecutorService == null) {
            mExecutorService = new ThreadPoolExecutor(3, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<>(), new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }
        System.out.println("mExecutorService=" + mExecutorService);
        return mExecutorService;
    }


    public static void main(String[] args) {
        for (int j = 0; j <= 3; j++) {
            ThreadPoolTestException exception = new ThreadPoolTestException();
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
                exception.executorService().execute(runnable);
            }
        }
    }
}
