package zhiwenyan.cmccaifu.com.android2017.annotation;

/**
 * Created by yanzhiwen on 2017/10/27.
 */

public abstract class HttpCallback<T> {
    public abstract void onSuccess(T result);
}
