package zhiwenyan.cmccaifu.com.android2017.okhttp.download;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Description:
 * Data：3/21/2018-1:44 PM
 *
 * @author: yanzhiwen
 */
public class DownloadDispatcher {
    private static final String TAG = "DownloadDispatcher";
    private static final DownloadDispatcher INSTANCE = new DownloadDispatcher();
    private final Deque<DownloadTask> readyTasks = new ArrayDeque<>();

    private final Deque<DownloadTask> runningTasks = new ArrayDeque<>();

    private final Deque<DownloadTask> stopTasks = new ArrayDeque<>();

    private DownloadDispatcher() {
    }

    public static DownloadDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * @param url      下载的地址
     * @param callBack 回调接口
     */
    public void startDownload(String url, DownloadCallback callBack) {
        Call call = OkHttpManager.getInstance().asyncCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //获取文件的大小
                long contentLength = response.body().contentLength();
                Log.i(TAG, "onResponse:contentLength= " + contentLength);
                if (contentLength <= -1) {
                    return;
                }
                DownloadTask downloadTask = new DownloadTask(url, contentLength, callBack);
                downloadTask.init();
                runningTasks.add(downloadTask);
            }
        });
    }


    /**
     * @param downLoadTask
     */
    public void recyclerTask(DownloadTask downLoadTask) {
        runningTasks.remove(downLoadTask);
        //参考OkHttp的Dispatcher()的源码
        //readyTasks.
    }

    public void stopDownLoad(String url) {
        //这个停止是不是这个正在下载的
    }
}
