package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;


/**
 * Description:
 * Data：1/19/2018-4:12 PM
 *
 * @author: yanzhiwen
 */
public interface Callback<T> {
    void onResponse(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);
}
