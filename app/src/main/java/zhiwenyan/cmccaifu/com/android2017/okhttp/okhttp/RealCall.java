package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;


import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor.BridgeInterceptor;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor.CallServerInterceptor;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor.Interceptor;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor.RealInterceptorChain;


/**
 * Description:
 * Data：11/20/2017-2:25 PM
 * Author: yanzhiwen
 */
public class RealCall implements Call {
    private final Request mOrginRequest;
    private final OkHttpClient mClient;

    public RealCall(Request request, OkHttpClient okHttpClient) {
        mOrginRequest = request;
        mClient = okHttpClient;

    }

    public static Call newCall(Request request, OkHttpClient okHttpClient) {
        return new RealCall(request, okHttpClient);
    }

    @Override
    public void enqueue(CallBack callBack) {
        AsyncCall asyncCall = new AsyncCall(callBack);
        //交给线程池
        mClient.mDispatcher.enqueue(asyncCall);
    }

    @Override
    public Response execute() {
        return null;
    }

    final class AsyncCall extends NamedRunnable {
        CallBack mCallBack;

        public AsyncCall(CallBack callBack) {
            mCallBack = callBack;
        }

        @Override
        public void execute() {
            //访问网络Request->Response
            Log.e("TAG", "execute: ");
            //基于HttpURLConnection okHttp基于socket和okio
            try {
                List<Interceptor> interceptors = new ArrayList<>();
                interceptors.add(new BridgeInterceptor());
                interceptors.add(new CallServerInterceptor());
                Interceptor.Chain chain = new RealInterceptorChain(interceptors, 0, mOrginRequest);
                Response response = chain.proceed(mOrginRequest);
                mCallBack.onResponse(RealCall.this, response);
            } catch (IOException e) {
                e.printStackTrace();
                mCallBack.onFailure(RealCall.this, e);
            }
        }
    }
}
