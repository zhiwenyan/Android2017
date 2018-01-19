package zhiwenyan.cmccaifu.com.android2017.retrofit.simple2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.retrofit.simple1.RetrofitClient;
import zhiwenyan.cmccaifu.com.android2017.retrofit.simple1.UserInfo;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // OkHttp +RxJava + Retrofit
        RetrofitClient.getServiceApi().userLogin("Steven", "940223")
                .enqueue(new HttpCallback<UserInfo>() {
                    @Override
                    public void onSucceed(UserInfo result) {
                        // 成功
                        Toast.makeText(MainActivity.this, "成功" + result.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(String code, String msg) {
                        // 失败
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
    }
}
