package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import zhiwenyan.cmccaifu.com.android2017.R;

public class OKHttpSourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_source);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().builder();
//        RequestBody requestBody=new RequestBody().addParam("",“);
        Request request = new Request.Builder().url("https://www.baidu.com/").builder();
        okHttpClient.newCall(request).enqueue(new CallBack() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: 出错了"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG", "onResponse: " + response.string());
            }
        });
    }
}
