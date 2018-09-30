package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Description:线程调度器
 * Data：2/8/2018-1:45 PM
 *
 * @author: yanzhiwen
 */
public abstract class Schedulers {
    static Schedulers IO;

    static {

        IO = new IOSchedulers();
    }

    public static Schedulers io() {
        return IO;
    }


    public abstract void scheduleDirect(Runnable runnable);

    private static class IOSchedulers extends Schedulers {
        ExecutorService service;

        public IOSchedulers() {
            //线程池
            this.service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {

                    return new Thread(r);
                }
            });
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            service.execute(runnable);
        }
    }
}
