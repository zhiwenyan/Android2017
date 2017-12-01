package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * Description:负责app的 部分线程
 * Data：11/29/2017-4:57 PM
 *
 * @author: yanzhiwen
 */
public class DownLoadRunnable implements Runnable {
    private static final int STATUS_DOWNLOADING = 1;
    private static final int STATUS_STOP = 2;
    private final long start;
    private final long end;
    private final int threadId;
    private final String url;
    private DownLoadCallBack mDownLoadCallBack;
    private int mSTATUS = STATUS_DOWNLOADING;
    private long mProgress;

    public DownLoadRunnable(long start, long end, int threadId, String url, DownLoadCallBack downLoadCallBack) {
        this.start = start;
        this.end = end;
        this.threadId = threadId;
        this.url = url;
        this.mDownLoadCallBack = downLoadCallBack;
        Log.i("TAG", "DownLoadRunnable: " + this.toString());
    }


    @Override
    public void run() {
        RandomAccessFile randomAccessFile = null;
        InputStream inputStream = null;
        //只读写自己的内容
        try {
            Response response = OkHttpManager.getInstance().syncResponse(url, start, end);
            Log.i("DownLoadRunnable", "run: " + response.body().contentLength() + "," + start + " ," + end);
            inputStream = response.body().byteStream();
            //写数据
            File file = FileManager.getFileManager().getFile(url);
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(start);
            int len = 0;
            byte[] bytes = new byte[1024 * 10];
            while ((len = inputStream.read(bytes)) != -1) {
                //保存进度，做断点
                mProgress = +len;
                randomAccessFile.write(bytes, 0, len);
                if (mSTATUS == STATUS_STOP) {
                    break;
                }
            }
            mDownLoadCallBack.onSuccess(file);
        } catch (IOException e) {
            mDownLoadCallBack.onFailure(e);
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //存到数据库中，怎么存？
        }
    }

    @Override
    public String toString() {
        return "DownLoadRunnable{" +
                "start=" + start +
                ", end=" + end +
                ", threadId=" + threadId +
                ", url='" + url + '\'' +
                ", mDownLoadCallBack=" + mDownLoadCallBack +
                '}';
    }

    public void stop() {
        mSTATUS = STATUS_STOP;
    }
}
