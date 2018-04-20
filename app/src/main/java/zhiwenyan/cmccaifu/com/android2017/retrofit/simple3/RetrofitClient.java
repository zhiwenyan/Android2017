package zhiwenyan.cmccaifu.com.android2017.retrofit.simple3;


import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author yanzhiwen
 */

public class RetrofitClient {
    private final static ServiceApi mServiceApi;

    static {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@NonNull String message) {
                        Log.e("TAG", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        // 各种套路和招式 ，发现问题解决问题，基础，源码的理解
        // 1. 没打印？
        // 2. 数据格式不一致？成功 data 是个对象，不成功 data 是个 String
        // 3. 还有就是 baseUrl 问题？ (Retrofit 找不到任何入口可以修改)
        // 3.1 不同的 baseUrl 构建不同的 Retrofit 对象 （直不应该首选）
        // 3.2 自己想办法，取巧也行走漏洞
        Retrofit retrofit = new Retrofit.Builder()
                // 访问后台接口的主路径
                .baseUrl("http://192.168.10.92:8080/OkHttpServer/")
                // 添加解析转换工厂,Gson 解析，Xml解析，等等
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                //支持RxJava、Call->Observable 怎么做到的？ 采用了Adapter设计模式
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // 创建一个 实例对象
        mServiceApi = retrofit.create(ServiceApi.class);
    }

    /**
     * ？？？
     *
     * @param <T>
     * @return
     */
//    public static <T> ObservableTransformer<Result<T>, T> transformer() {
//        return new ObservableTransformer<Result<T>, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<Result<T>> resultObservable) {
//                return resultObservable.flatMap(new Function<Result<T>, ObservableSource<T>>() {
//                    @Override
//                    public ObservableSource<T> apply(Result<T> tResult) throws Exception {
//                        if (tResult.isOk()) {
//                            //返回成功
//                            return createObservable(tResult.data);
//                        } else {
//                            //返回失败
//                            return Observable.error(new ErrorHandler.ServiceError(tResult.getMsg(),
//                                    tResult.getCode()));
//                        }
//                    }
//                }).subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }

    private static <T> ObservableSource<T> createObservable(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                emitter.onNext(data);
                emitter.onComplete();
            }
        });
    }


    public static ServiceApi getServiceApi() {
        return mServiceApi;
    }

}
