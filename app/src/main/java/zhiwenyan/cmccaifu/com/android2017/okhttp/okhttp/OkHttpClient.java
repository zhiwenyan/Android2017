package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;


/**
 * Description:
 * Data：11/20/2017-2:25 PM
 * Author: yanzhiwen
 */
public class OkHttpClient {
    protected Dispatcher mDispatcher;

    public OkHttpClient(Builder builder) {
        mDispatcher = builder.mDispatcher;
    }

    public OkHttpClient() {
        this(new Builder());
    }

    public Call newCall(Request request) {
        return RealCall.newCall(request, this);
    }

    public static class Builder {
        public Dispatcher mDispatcher;

        public Builder() {
            mDispatcher = new Dispatcher();
        }

        public OkHttpClient builder() {
            return new OkHttpClient(this);
        }
    }
}
