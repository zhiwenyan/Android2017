package zhiwenyan.cmccaifu.com.android2017.retrofit.simple1;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhiwenyan on 2017/12/16.
 */

public class RetrofitClient {
    private static final String TAG = RetrofitClient.class.getSimpleName();
    private final static ServiceApi mServiceApi;

    static {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor(message -> Log.e(TAG, message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        // 各种套路和招式 ，发现问题解决问题，基础，源码的理解
        // 1. 没打印？
        // 2. 数据格式不一致？成功 data 是个对象，不成功 data 是个 String
        // 3. 还有就是 baseUrl 问题？ (Retrofit 找不到任何入口可以修改)
        //        3.1 不同的 baseUrl 构建不同的 Retrofit 对象 （直不应该首选）
        //        3.2 不同的baseUrl 怎么弄？？？自己想办法，取巧也行走漏洞
        Retrofit retrofit = new Retrofit.Builder()
                // 访问后台接口的主路径
                .baseUrl("http://192.168.10.92:8080/SpringMVC2018/")
                // 添加解析转换工厂,Gson 解析，Xml解析，等等
                .addConverterFactory(GsonConverterFactory.create())
                // 添加 OkHttpClient,不添加默认就是 光杆 OkHttpClient
               // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        // 创建一个 实例对象
        mServiceApi = retrofit.create(ServiceApi.class);
    }

    public static ServiceApi getServiceApi() {
        return mServiceApi;
    }

}
