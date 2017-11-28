package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.postFile;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zhiwenyan.cmccaifu.com.android2017.R;

public class PostFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_file);
        String url = "https://api.saiwuquan.com/api/upload";
        File file = new File(Environment.getExternalStorageDirectory(), "test.png");
        OkHttpClient httpClient = new OkHttpClient();
        // 构建请求 Body , 这个我们之前自己动手写过
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("platform", "android");
        builder.addFormDataPart("file", file.getName(),
                RequestBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));

        ExMultipartBody exMultipartBody=new ExMultipartBody(builder.build());
        // 构建一个请求
        final Request request = new Request.Builder()
                .url(url)
                .post(exMultipartBody).build();
        // new RealCall 发起请求
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", response.body().string());
            }
        });

    }

    private String guessMimeType(String absolutePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();

        String mimType = fileNameMap.getContentTypeFor(absolutePath);
        if (TextUtils.isEmpty(mimType)) {
            return "application/octet-stream";
        }
        Log.i("TAG", "guessMimeType: " + mimType);
        return mimType;
    }
}
