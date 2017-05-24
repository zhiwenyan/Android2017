package zhiwenyan.cmccaifu.com.android2017.retrofit.Http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaseCallBack<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            responseResult(response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        //responseResult((T) t.getMessage());
    }

    protected T responseResult(T t) {
        return t;
    }
}
