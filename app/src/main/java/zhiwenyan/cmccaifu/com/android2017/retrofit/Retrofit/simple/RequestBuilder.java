package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

import okhttp3.HttpUrl;
import okhttp3.Request;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.ParameterHandler;

/**
 * Description:
 * Data：1/19/2018-4:55 PM
 *
 * @author: yanzhiwen
 */
public class RequestBuilder {
    private final ParameterHandler<Object>[] parameterHandlers;
    private final Object[] args;
    private HttpUrl.Builder httpUrl;

    public RequestBuilder(String baseUrl, String relativeUrl, String httpMethod,
                          ParameterHandler<?>[] parameterHandlers, Object[] args) {
        this.parameterHandlers = (ParameterHandler<Object>[]) parameterHandlers;
        this.args = args;
        this.httpUrl = HttpUrl.parse(baseUrl + relativeUrl).newBuilder();
    }

    public Request build() {
        int count = parameterHandlers.length;
        for (int i = 0; i < count; i++) {
            ParameterHandler parameterHandler = parameterHandlers[i];
            //需要把值传过去  username ==steven
            parameterHandler.apply(this, args[i]);
        }
        //构建一个请求
        Request request = new Request.Builder().url(httpUrl.build()).build();
        return request;
    }


    public void addQueryName(String key, String value) {
        //username=steven&password=123456 拼接参数
        httpUrl.addQueryParameter(key, value);

    }

}
