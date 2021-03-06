package zhiwenyan.cmccaifu.com.android2017.okhttp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.cache.CacheActivity;

public class   OkhttpActivity extends BaseActivity {
    @BindView(R.id.resultTv)
    TextView mResultTv;
    private OkHttpClient mOkHttpClient = new OkHttpClient();


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected int getLayoutId() {
        return R.layout.activity_okhttp;
    }

    @OnClick({R.id.getBtn, R.id.postBtn, R.id.accessHeadersBtn, R.id.cacheBtn, R.id.networkCacheBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getBtn:
                enqueuegGet();
                break;
            case R.id.postBtn:
                post("Jesse", "Steven");
                break;
            case R.id.accessHeadersBtn:
                accessHeaders();
                break;
            case R.id.cacheBtn:
                cache();
                break;
            case R.id.networkCacheBtn:
                startActivity(new Intent(this, CacheActivity.class));
                break;

        }
    }


    private void cache() {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(new File("CacheResponse.tmp"), cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                updateUI(response.body().string());
            }
        });

    }

    private void accessHeaders() {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                updateUI("Server:" + response.header("Server") + "\n " + "Date:" +
                        response.header("Date") + "\n" + "Vary:" + response.header("Vary"));
            }
        });
    }

    private void post(String player1, String player2) {
        String json = "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://www.roundsapp.com/post")
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                updateUI(e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                updateUI(response.body().string());
            }
        });

    }

    private void enqueuegGet() {
        //异步
        String url = "https://www.oschina.net/action/apiv2/tweet_list?&order=1&type=1";
        final Request request = new Request.Builder()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                updateUI(e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                updateUI(response.body().string());
            }
        });
    }

    private void updateUI(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultTv.setText(s);
            }
        });
    }
}
