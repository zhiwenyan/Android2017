package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

import okhttp3.Request;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.ParameterHandler;

/**
 * Description:
 * Data：1/19/2018-4:55 PM
 *
 * @author: yanzhiwen
 */
public class RequestBuilder {
    ParameterHandler<?>[] parameterHandlers;
    Object[] args;

    public RequestBuilder(String baseUrl, String relativeUrl, String httpMethod, ParameterHandler<?>[] parameterHandlers, Object[] args) {
        this.parameterHandlers = parameterHandlers;
        this.args = args;
    }

    public Request build() {
        int count = parameterHandlers.length;
        for (int i = 0; i < count; i++) {
            ParameterHandler parameterHandler = parameterHandlers[i];
            //username ==steven
            parameterHandler.apply(this, args[i]);
        }
        return null;
    }


    public void addQueryName(String key, String value) {
        //username=steven password=123456
    }
}
