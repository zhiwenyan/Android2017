package zhiwenyan.cmccaifu.com.android2017.retrofit.simple6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.retrofit.simple1.RetrofitClient;

public class Retrofit2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit3);
        String username = "steven";
        String password = "123456";
        Call<Person> call = RetrofitClient.getServiceApi().helloWorld(username,password);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.i("TAG", "onResponse: " + response.body().getPassword());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.i("TAG", "onFailure: " + t.getMessage());

            }
        });
    }
}
