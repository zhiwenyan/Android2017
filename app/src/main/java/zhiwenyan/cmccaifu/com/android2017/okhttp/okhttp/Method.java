package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

/**
 * Description:
 * Data：11/20/2017-2:39 PM
 * Author: yanzhiwen
 */
public enum Method {
    POST("post"), GET("get"), HEAD("head"), PUT("put"), DELETE("delete"), PATCH("patch");
    public  String name;

    Method(String name) {
        this.name = name;
    }

    public boolean doOutput() {
        switch (this) {
            case GET:
            case PUT:
            case POST:
                return true;
            default:
                break;
        }
        return false;

    }
}
