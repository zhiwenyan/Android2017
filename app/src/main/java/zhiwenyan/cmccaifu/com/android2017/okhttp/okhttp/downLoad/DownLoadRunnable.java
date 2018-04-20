package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * Description:
 * Data：11/29/2017-4:57 PM
 *
 * @author: yanzhiwen
 */
public class DownLoadRunnable implements Runnable {
    private static final String TAG = "DownLoadRunnable";
    private static final int STATUS_DOWNLOADING = 1;
    private static final int STATUS_STOP = 2;
    private final long start;
    private final long end;
    private final int threadId;
    private final String url;
    private DownLoadCallBack mDownLoadCallBack;
    private int mSTATUS = STATUS_DOWNLOADING;
    private volatile long mProgress;
    private long mTotalProgress;

    public DownLoadRunnable(long start, long end, int threadId, String url, DownLoadCallBack downLoadCallBack) {
        this.start = start;
        this.end = end;
        this.threadId = threadId;
        this.url = url;
        this.mDownLoadCallBack = downLoadCallBack;
    }


    @Override
    public void run() {
        RandomAccessFile randomAccessFile = null;
        InputStream inputStream = null;

        //只读写自己的内容
        try {
            Response response = OkHttpManager.getInstance().syncResponse(url, start, end);
            Log.i(TAG, "DownloadRunnable: " + "contentLength=" + response.body().contentLength()
                    + "start=" + start + "end=" + end + "threadId=" + threadId);
            inputStream = response.body().byteStream();
            //写数据
            //File file = FileManager.getFileManager().getFile(url);
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "shoujibaidu.apk");
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(start);
            int len = 0;
            byte[] bytes = new byte[1024 * 1024 * 10];
            while ((len = inputStream.read(bytes)) != -1) {
                if (mSTATUS == STATUS_STOP) {
                    break;
                }
                randomAccessFile.write(bytes, 0, len);
                //保存进度，做断点
                mProgress = +len;
                Log.i(TAG, "run: mProgress=" + mProgress);
            }
            mTotalProgress += mProgress;
            Log.i(TAG, "run: mTotalProgress=" + mTotalProgress);
            mDownLoadCallBack.onSuccess(file);
        } catch (IOException e) {
            mDownLoadCallBack.onFailure(e);
            e.printStackTrace();
        } finally {
            Utils.close(randomAccessFile);
            Utils.close(inputStream);
            //存到数据库中，怎么存？
        }
    }

    public void stop() {
        mSTATUS = STATUS_STOP;
    }
}
