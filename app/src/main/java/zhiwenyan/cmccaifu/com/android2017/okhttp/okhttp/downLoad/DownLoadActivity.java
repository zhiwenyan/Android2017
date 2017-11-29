package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zhiwenyan.cmccaifu.com.android2017.R;

public class DownLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        //多线程的逻辑是什么？ 每个线程只负责读取单独的内容

        //多线程+断点

        final OkHttpManager okHttpManager = new OkHttpManager();
        Call call = okHttpManager.asyncCall("http://gdown.baidu.com/data/wisegame/67b78c90d012ae54/shoujibaidu_24449280.apk");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                Log.i("TAG", "onResponse: contLength=" + response.body().contentLength());
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "shoujibaidu.apk");
                OutputStream outputStream = new FileOutputStream(file);
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                    Log.i("TAG", "onResponse: " + len);

                }
                inputStream.close();
                outputStream.close();
            }
        });
    }
}
