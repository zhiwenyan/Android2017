package zhiwenyan.cmccaifu.com.android2017.Http;



public interface HttpListener {
    void onSuccess(Object result);

    void onFailure(int errorType, String message);
}
