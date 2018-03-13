package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:每一个app的下载
 * 这个类最终需要复用的，不复用也行
 * Data：11/29/2017-4:58 PM
 *
 * @author: yanzhiwen
 */
public class DownLoadTask {
    private String url;
    private long contentLength;
    private List<DownLoadRunnable> mDownLoadRunnables;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private DownLoadCallBack mDownLoadCallBack;
    private ThreadPoolExecutor executorService;
    private volatile int mSuccessNumber;

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(
                    0, THREAD_SIZE, 30, TimeUnit.SECONDS,
                    new SynchronousQueue<>(), r -> {
                Thread thread = new Thread(r, "DownLoadTask");
                thread.setDaemon(false);
                return thread;
            });
        }
        return executorService;
    }


    public DownLoadTask(String url, long contentLength, DownLoadCallBack callBack) {
        this.url = url;
        this.contentLength = contentLength;
        this.mDownLoadRunnables = new ArrayList<>();
        this.mDownLoadCallBack = callBack;
    }

    /**
     * 初始化
     */
    public void init() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            //计算每个线程的下载内容
            long threadSize = contentLength / THREAD_SIZE;
            Log.i("TAG", "init: " + "threadSize=" + threadSize);
            //初始化的时候，要读取数据库
            long start = i * threadSize;
            long end = start + threadSize - 1;
            Log.i("TAG", "init: " + "start=" + start + "end=" + end);
            if (i == THREAD_SIZE - 1) {
                end = contentLength - 1;
            }
            DownLoadRunnable downLoadRunnable = new DownLoadRunnable(start, end, i, url, new DownLoadCallBack() {
                @Override
                public void onFailure(IOException e) {
                    //一个apk，下载里面有一个线程异常了，处理异常，把其它线程停止
                    mDownLoadCallBack.onFailure(e);
                }

                @Override
                public void onSuccess(File file) {
                    //线程同步下
                    synchronized (DownLoadTask.this) {
                        mSuccessNumber += 1;
                        if (mSuccessNumber == THREAD_SIZE) {
                            mDownLoadCallBack.onSuccess(file);
                            DownloadDispatcher.getInstance().recyclerTask(DownLoadTask.this);
                            //下载完毕需要清除数据库文件存储
                        }
                    }
                }
            });
            //通过线程池去执行
            executorService().execute(downLoadRunnable);
        }
    }

    public void stop() {
        for (DownLoadRunnable downLoadRunnable : mDownLoadRunnables) {
            downLoadRunnable.stop();

        }
    }
}
