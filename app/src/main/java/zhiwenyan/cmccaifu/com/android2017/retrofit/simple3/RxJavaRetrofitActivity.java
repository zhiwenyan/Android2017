package zhiwenyan.cmccaifu.com.android2017.retrofit.simple3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * RxJava+okHttp+Retrofit 封装
 */
public class RxJavaRetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_retrofit);
//        RetrofitClient.getServiceApi().userLogin("Steven", "888888")
//                // .subscribeOn().observeOn() ->compose
//                //返回值一个T类型
//                .compose(RetrofitClient.<UserInfo>transformer())
//                .subscribe(new BaseSubscriber<UserInfo>() {
//                    @Override
//                    public void onNext(UserInfo userInfo) {
//                        Log.i("TAG", "onNext: "+userInfo.toString());
//                    }
//                });

    }
}
