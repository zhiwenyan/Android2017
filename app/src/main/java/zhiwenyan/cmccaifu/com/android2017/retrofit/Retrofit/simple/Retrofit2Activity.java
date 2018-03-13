package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import zhiwenyan.cmccaifu.com.android2017.R;


public class Retrofit2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
        RetrofitClient.getServiceApi().userLogin("Steven", "123456").enqueue(new Callback<UserLoginResult>() {
                    @Override
                    public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                        Log.i("TAG", "onResponse: " + response.body.toString());
                    }

                    @Override
                    public void onFailure(Call<UserLoginResult> call, Throwable t) {

                    }
                });

    }
}
