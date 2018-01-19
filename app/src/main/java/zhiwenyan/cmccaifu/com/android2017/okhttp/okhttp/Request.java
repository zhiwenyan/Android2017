package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:构建Request请求参数 建造者模式
 * Data：11/20/2017-2:25 PM
 * Author: yanzhiwen
 */
public class Request {
    public final String url;
    public final Method method;
    public final Map<String, String> headers;
    public final RequestBody mRequestBody;

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.mRequestBody = builder.mRequestBody;
    }

    public void header(String key, String value) {
        headers.put(key, value);
    }

    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public RequestBody getRequestBody() {
        return mRequestBody;
    }

    public static class Builder {
        //String url post参数 body 请求头
        String url;
        Method method;
        Map<String, String> headers = new HashMap<>();
        RequestBody mRequestBody;

        public Builder() {
            method = Method.GET;
        }

        protected Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder get() {
            return this;
        }

        public Builder post(RequestBody requestBody) {
            method = Method.POST;
            this.mRequestBody = requestBody;
            return this;
        }

        public Builder header(String value, String key) {
            headers.put(value, key);
            return this;
        }

        public Request builder() {
            return new Request(this);
        }

    }
}
