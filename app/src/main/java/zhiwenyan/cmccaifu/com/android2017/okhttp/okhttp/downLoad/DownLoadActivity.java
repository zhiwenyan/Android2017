package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Button btn1, btn2;
    private final String url = "http://gdown.baidu.com/data/wisegame/67b78c90d012ae54/shoujibaidu_24449280.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //多线程的逻辑是什么？ 每个线程只负责读取单独的内容
                //多线程+断点
                final OkHttpManager okHttpManager = OkHttpManager.getInstance();
                Call call = okHttpManager.asyncCall(url);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.i("DownLoadActivity", "onFailure: 单线程下载失败");

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
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
                        Log.i("DownLoadActivity", "onResponse: 单线程下载成功");

                    }
                });

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManager.getFileManager().init(DownLoadActivity.this);
                DownloadDispatcher.getInstance().startDownload(url, new DownLoadCallBack() {
                    @Override
                    public void onFailure(IOException e) {
                        Log.i("DownLoadActivity", "onFailure: 多线程下载失败");
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.i("DownLoadActivity", "onSuccess:多线程下载成功 " + file.getAbsolutePath());

                    }
                });
            }
        });
    }
}
