package zhiwenyan.cmccaifu.com.android2017.okhttp.download;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

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
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileManager.getInstance().init(DownLoadActivity.this);
                DownloadDispatcher.getInstance().startDownload(url, new DownloadCallback() {
                    @Override
                    public void onFailure(Exception e) {
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
