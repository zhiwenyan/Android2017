package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

import android.util.Log;

import java.io.IOException;

import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.ServiceMethod;

/**
 * Description:
 * Data：1/19/2018-4:17 PM
 *
 * @author: yanzhiwen
 */
public class OkHttpCall<T> implements Call<T> {
    final ServiceMethod mServiceMethod;
    final Object[] args;

    public OkHttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.mServiceMethod = serviceMethod;
        this.args = args;
    }

    @Override
    public void enqueue(final Callback<T> callback) {
        //发起一个请求 然后给一个回调
        Log.i("TAG", "enqueue: 正式发送请求");
        okhttp3.Call call = mServiceMethod.createNewCall(args);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                if (callback != null) {
                    callback.onFailure(OkHttpCall.this, e);
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                //解析 Response ->Response<T>
//               Log.i("TAG", "onResponse: " + response.body().string());
                //涉及到解析，不能在这里写死 Retrofit源码有个CovertFactory
                Response resp = new Response();
                resp.body = mServiceMethod.parseBody(response.body());
                if(callback!=null){
                    callback.onResponse(OkHttpCall.this,resp);
                }
            }
        });
    }

}
