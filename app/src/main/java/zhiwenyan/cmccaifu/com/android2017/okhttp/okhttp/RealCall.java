package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;


import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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

            try {
                URL url = new URL(mOrginRequest.url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection instanceof HttpsURLConnection) {
                    urlConnection = (HttpsURLConnection) url.openConnection();

                }
                //  httpURLConnection.setReadTimeout(8*1000);
                urlConnection.setRequestMethod(mOrginRequest.method.name);
                urlConnection.setDoInput(mOrginRequest.method.doOutPut());
                //写
                RequestBody requestBody = mOrginRequest.mRequestBody;
                if (requestBody != null) {
                    //头信息
                    urlConnection.setRequestProperty("Content-Type", requestBody.getContentType());
                    urlConnection.setRequestProperty("Content-length", Long.toString(requestBody.getContentLength()));
                }
                //连接
                urlConnection.connect();
                if (requestBody != null) {
                    requestBody.onWriteBody(urlConnection.getOutputStream());
                }
                //写内容
                int code = urlConnection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = urlConnection.getInputStream();
                    //转成
                    Response response = new Response(inputStream);
                    mCallBack.onResponse(RealCall.this, response);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mCallBack.onFailure(RealCall.this, e);
            }

        }
    }
}
