package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor;


import java.io.IOException;

import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Request;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Response;

/**
 * Created by Steven on 2017/11/19.
 */

public interface Interceptor {
    Response intercept(Chain chain) throws IOException;

    interface Chain {
        Request request();

        Response proceed(Request request) throws IOException;
    }
}
