package zhiwenyan.cmccaifu.com.android2017.okhttp.download;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * Description:
 * Data：3/21/2018-1:45 PM
 *
 * @author: yanzhiwen
 */
public class DownloadRunnable implements Runnable {
    private static final int STATUS_DOWNLOADING = 1;
    private static final int STATUS_STOP = 2;
    private String url;
    private int threadId;
    private long start;
    private long end;
    private DownloadCallback downloadCallback;
    private int mStatus = STATUS_DOWNLOADING;
    private long mProgress;

    public DownloadRunnable(String url, int threadId, long start, long end, DownloadCallback downloadCallback) {
        this.url = url;
        this.threadId = threadId;
        this.start = start;
        this.end = end;
        this.downloadCallback = downloadCallback;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        try {
            Response response = OkHttpManager.getInstance().syncResponse(url, start, end);
            Log.i("TAG", "DownloadRunnable: " + "contentLength=" + response.body().contentLength()
                    + "start=" + start + "end=" + end + "threadId=" + threadId);
            inputStream = response.body().byteStream();
            File file = FileManager.getInstance().getFile(url);
            randomAccessFile = new RandomAccessFile(file, "rwd");
            //seek从哪里开始
            randomAccessFile.seek(start);
            int len = 0;
            byte[] bytes = new byte[10 * 1024 * 1024];
            while ((len = inputStream.read(bytes)) != -1) {
                if (mStatus == STATUS_STOP)
                    break;
                //保存下进度，做断点
                mProgress += len;
                randomAccessFile.write(bytes, 0, len);
            }
            downloadCallback.onSuccess(file);
        } catch (IOException e) {
            e.printStackTrace();
            downloadCallback.onFailure(e);
        } finally {
            Utils.close(inputStream);
            Utils.close(randomAccessFile);
            //保存到数据库 怎么存？？
        }
    }

    public void stop() {
        mStatus = STATUS_STOP;
    }
}
