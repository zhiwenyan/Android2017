package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Description:
 * Data：11/29/2017-4:57 PM
 *
 * @author: yanzhiwen
 */
public class DownloadDispatcher {
    private final Deque<DownLoadTask> readyAsyncCalls = new ArrayDeque<>();

    private final Deque<DownLoadTask> runningAsyncCalls = new ArrayDeque<>();

    private final Deque<DownLoadTask> runningSyncCalls = new ArrayDeque<>();
    private static final DownloadDispatcher sDownloadDispatcher = new DownloadDispatcher();

    private DownloadDispatcher() {

    }

    public static DownloadDispatcher getInstance() {
        return sDownloadDispatcher;
    }

    /**
     * 开始下载
     *
     * @param url
     * @param callBack
     */
    public void startDownload(final String url, final DownLoadCallBack callBack) {
        Call call = OkHttpManager.getInstance().asyncCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                long contentLength = response.body().contentLength();
                if (contentLength <= -1) {
                    return;
                }
                DownLoadTask downLoadTask = new DownLoadTask(url, contentLength, callBack);
                downLoadTask.init();
                runningAsyncCalls.add(downLoadTask);
            }
        });
    }

    /**
     * @param downLoadTask
     */
    public void recyclerTask(DownLoadTask downLoadTask) {
        runningAsyncCalls.remove(downLoadTask);
        //参考OkHttp的Dispatcher()的源码
    }

    public void stopDownLoad(String url) {
        //这个停止是不是这个正在下载的
    }
}
