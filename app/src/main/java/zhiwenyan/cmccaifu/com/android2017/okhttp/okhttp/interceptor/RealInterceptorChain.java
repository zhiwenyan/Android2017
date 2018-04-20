package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor;

import java.io.IOException;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Request;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.Response;

/**
 * Created by hcDarren on 2017/11/19.
 */

public class RealInterceptorChain implements Interceptor.Chain {
    final List<Interceptor> interceptors;
    final int index;
    final Request request;

    public RealInterceptorChain(List<Interceptor> interceptors, int index, Request request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response proceed(Request request) throws IOException {
        RealInterceptorChain next = new RealInterceptorChain(interceptors, index + 1, request);
        Interceptor interceptor = interceptors.get(index);
        Response response = interceptor.intercept(next);
        return response;
    }
}
