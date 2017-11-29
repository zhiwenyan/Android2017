package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.cache;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zhiwenyan.cmccaifu.com.android2017.R;

public class CacheActivity extends AppCompatActivity {
    private OkHttpClient mOkHttpClient;
    String url = "https://api.saiwuquan.com/api/appv2/sceneModel";
    private android.widget.TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        mTv = (android.widget.TextView) findViewById(R.id.tv);
        File file = new File(Environment.getExternalStorageDirectory(), "cache");
        Cache cache = new Cache(file, 100 * 1024 * 1024);
        mOkHttpClient = new OkHttpClient.Builder()
                //加载最前
                .cache(cache)
                // .addInterceptor(new CacheRequestInterceptor(this))
                //加载最后 数据缓存，过期时间 30S
                .addNetworkInterceptor(new CacheResponseIntercptor())
                .build();
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });


    }

    private void requestData() {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG", "onResponse: " + response.body().string());
                // 都是有 第一把，第二把没有网络的了只有缓存的 (30s 以内)，过了 30s 之后又会有网络的了（会再请求更新）
                Log.i("TAG", "onResponse: " + response.cacheResponse() + "," + response.networkResponse());
            }
        });
    }
}
