package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Description:
 * Data：11/29/2017-3:36 PM
 *
 * @author: yanzhiwen
 */
public class OkHttpManager {
    public Call asyncCall(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        return okHttpClient.newCall(request);
    }

}
