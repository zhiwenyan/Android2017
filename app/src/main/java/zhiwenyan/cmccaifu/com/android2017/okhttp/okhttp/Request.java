package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Data：11/20/2017-2:25 PM
 * Author: yanzhiwen
 */
public class Request {
    final String url;
    final Method method;
    final Map<String, String> headers;
    final RequestBody mRequestBody;

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.mRequestBody = builder.mRequestBody;
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

        protected Builder get() {

            return this;
        }

        protected Builder post(RequestBody requestBody) {
            method = Method.POST;
            this.mRequestBody = requestBody;
            return this;
        }

        protected Builder header(String value, String key) {
            headers.put(value, key);
            return this;
        }


        protected Request builder() {
            return new Request(this);
        }

    }
}
