package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.postFile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * 静态代理设计模式
 * Description:
 * Data：11/28/2017-2:30 PM
 *
 * @author: yanzhiwen
 */
public class ExMultipartBody extends RequestBody {
    private RequestBody mRequestBody;
    private int mCurrentLength;
    private UploadProgressListener mListener;

    public ExMultipartBody(RequestBody requestBody, UploadProgressListener uploadProgressListener) {
        this.mRequestBody = requestBody;
        this.mListener = uploadProgressListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        //静态代理最终还是调用了代理对象的方法
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    /**
     * 往服务器写入东西，监听上传进度
     *
     * @param sink
     * @throws IOException
     */
    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        Log.i("TAG", "writeTo: 监听上传进度");
        //BufferedSink okio 相对服务器是一个输出流
        long contentLength = mRequestBody.contentLength();
        //获取当前写了多少数据？BufferedSink Sink(okio 就是io)就是一个服务器的输出流，写了多少数据还是不知道
        //又来一个代理ForwardingSink
        ForwardingSink forwardingSink = new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                mCurrentLength += byteCount;
                Log.e("tag", "mCurrentLength=" + mCurrentLength);
                if (mListener != null) {
                    mListener.onProgress(contentLength, mCurrentLength);
                }

                super.write(source, byteCount);
            }
        };
        //转一把
        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();

    }
}
