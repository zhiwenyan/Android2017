package zhiwenyan.cmccaifu.com.android2017.handlerThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;

import java.util.Arrays;
import java.util.List;

/**
 * Description: HandlerThread 所做的就是在新开的子线程中创建了 Looper，
 * 那它的使用场景就是 Thread + Looper 使用场景的结合，即：在子线程中执行耗时的、可能有多个任务的操作。
 * 它就是一个帮我们创建 Looper 的线程，让我们可以直接在线程中使用 Handler 来处理异步任务。
 * Data：5/8/2018-11:47 AM
 *
 * @author yanzhiwen
 */
public class DownloadThread extends HandlerThread implements Handler.Callback {

    private final String TAG = this.getClass().getSimpleName();
    private final String KEY_URL = "url";
    public static final int TYPE_START = 1;
    public static final int TYPE_FINISHED = 2;

    private Handler mWorkerHandler;
    private Handler mUIHandler;
    private List<String> mDownloadUrlList;

    public DownloadThread(final String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {    //执行初始化任务
        super.onLooperPrepared();
        mWorkerHandler = new Handler(getLooper(), this);    //使用子线程中的 Looper
        if (mUIHandler == null) {
            throw new IllegalArgumentException("Not set UIHandler!");
        }

        //将接收到的任务消息挨个添加到消息队列中
        for (String url : mDownloadUrlList) {
            Message message = mWorkerHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_URL, url);
            message.setData(bundle);
            mWorkerHandler.sendMessage(message);
        }
    }

    public void setDownloadUrls(String... urls) {
        mDownloadUrlList = Arrays.asList(urls);
    }

    public Handler getUIHandler() {
        return mUIHandler;
    }

    //注入主线程 Handler
    public DownloadThread setUIHandler(final Handler UIHandler) {
        mUIHandler = UIHandler;
        return this;
    }

    /**
     * 子线程中执行任务，完成后发送消息到主线程
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(final Message msg) {
        if (msg == null || msg.getData() == null) {
            return false;
        }

        String url = ( String ) msg.getData().get(KEY_URL);

        //下载开始，通知主线程
        Message startMsg = mUIHandler.obtainMessage(TYPE_START, "\n 开始下载 @" + System.currentTimeMillis() + "\n" + url);
        mUIHandler.sendMessage(startMsg);
        SystemClock.sleep(2000);    //模拟下载
        //下载完成，通知主线程
        Message finishMsg = mUIHandler.obtainMessage(TYPE_FINISHED, "\n 下载完成 @" + System.currentTimeMillis() + "\n" + url);
        mUIHandler.sendMessage(finishMsg);

        return true;
    }

    @Override
    public boolean quitSafely() {
        mUIHandler = null;
        return super.quitSafely();
    }
}