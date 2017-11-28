package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.postFile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * 静态代理设计模式
 * Description:
 * Data：11/28/2017-2:30 PM
 *
 * @author: yanzhiwen
 */
public class ExMultipartBody extends RequestBody {
    private RequestBody mRequestBody;

    public ExMultipartBody(RequestBody requestBody) {
        this.mRequestBody = requestBody;
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

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        Log.i("TAG", "writeTo: ");
        //BufferedSink okio 相对服务器是一个输出流
        long contentLength = mRequestBody.contentLength();
//        ForwardingSink forwardingSink = new ForwardingSink() {
//            @Override
//            public void write(Buffer source, long byteCount) throws IOException {
//                super.write(source, byteCount);
//            }
//        };
//        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
//        mRequestBody.writeTo(bufferedSink);
    }
}
