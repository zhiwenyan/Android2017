package zhiwenyan.cmccaifu.com.android2017.retrofit.Http;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Func1;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.Subject;
import zhiwenyan.cmccaifu.com.android2017.retrofit.service.MovieService;

public class HttpMethods {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5 * 1000;
    private Retrofit retrofit;
    private String info;

    private HttpMethods() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private final static HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }
//
//        public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count) {
//        retrofit.create(MovieService.class)
//                .getTopMovie(start, count)
//                .map(new HttpResultFunc<List<Subject>>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
 //   }

    public String getTopMovie(int start, int count) {
        retrofit.create(MovieService.class)
                .getTopMovie(start, count).enqueue(new Callback<HttpResult<List<Subject>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<Subject>>> call, Response<HttpResult<List<Subject>>> response) {
                if (response.isSuccessful()) {
                    info = response.body().getSubjects().get(0).getTitle();
                }
            }

            @Override
            public void onFailure(Call<HttpResult<List<Subject>>> call, Throwable t) {
                info = t.getMessage();
            }
        });
        return info;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }
}
