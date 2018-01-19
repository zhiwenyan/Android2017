package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor;

import android.util.Log;

import java.io.IOException;

import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Request;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.RequestBody;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Response;

/**
 * 桥接拦截器
 * Created by Steven on 2017/11/19.
 */

public class BridgeInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.i("TAG", "BridgeInterceptor");
        Request request = chain.request();
        // 添加一些请求头
        request.header("Connection", "keep-alive");
        // 做一些其他处理
        if (request.mRequestBody != null) {
            RequestBody requestBody = request.mRequestBody;
            //请求头
            request.header("Content-Type", requestBody.getContentType());
            request.header("Content-Length", Long.toString(requestBody.getContentLength()));
        }
        Response response = chain.proceed(request);

        return response;
    }
}
