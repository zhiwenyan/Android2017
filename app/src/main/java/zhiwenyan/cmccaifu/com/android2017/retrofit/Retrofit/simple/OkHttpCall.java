package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

import android.util.Log;

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
    public void enqueue(Callback<T> callback) {
        //发起一个请求 然后给一个回调
        Log.i("TAG", "enqueue: 正式发送请求");
        okhttp3.Call call=mServiceMethod.createNewCall(args);
    }

}
