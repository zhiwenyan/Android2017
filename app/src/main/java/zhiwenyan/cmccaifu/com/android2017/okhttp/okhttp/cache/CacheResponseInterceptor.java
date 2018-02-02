package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.cache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Description:自定义缓存拦截器
 * Data：11/29/2017-1:50 PM
 *
 * @author: yanzhiwen
 */
public class CacheResponseInterceptor implements Interceptor {
    public CacheResponseInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        response.newBuilder()
                //过期时间30s
                .removeHeader("Cache-Control")
                .removeHeader("Pragma")
                .addHeader("Cache-Control", "max-age=" + 30)
                .build();
        return response;
    }
}
