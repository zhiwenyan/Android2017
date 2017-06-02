package zhiwenyan.cmccaifu.com.android2017.Http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhiwenyan on 6/1/17.
 */

public class HttpUtils{
    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private static String Url;
    private String info;

    public HttpUtils() {
        mOkHttpClient = new OkHttpClient();
        mRequest = new Request.Builder()
                .url(Url)
                .build();
    }

    private static class singleInstance {
        private final static HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance(String url) {
        Url = url;
        return singleInstance.INSTANCE;
    }

    public String execute() {
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                info=response.body().toString();

            }
        });
        return info;
    }
}
