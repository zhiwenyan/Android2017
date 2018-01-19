package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

/**
 * Description:
 * Data：1/19/2018-2:20 PM
 *
 * @author: yanzhiwen
 */
public interface Call<T> {
    void enqueue(Callback<T> callback);
}
