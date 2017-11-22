package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor;


import java.io.IOException;

import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Request;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Response;

/**
 * Created by hcDarren on 2017/11/19.
 */

public class CacheInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // 本地有没有缓存，如果有没过期
        /*if(true){
            return new Response(new );
        }*/

        return chain.proceed(request);
    }
}
