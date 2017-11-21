package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:线程池
 * Data：11/21/2017-2:01 PM
 * Author: yanzhiwen
 */
public class Dispatcher {

    private @Nullable
    ExecutorService executorService;

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(r, "OKhttp Dispatcher");
                }
            });
        }
        return executorService;
    }


    public void enqueue(RealCall.AsyncCall call) {
        executorService().execute(call);
    }

}
