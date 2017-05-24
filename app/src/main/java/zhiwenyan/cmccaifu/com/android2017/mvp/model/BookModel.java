package zhiwenyan.cmccaifu.com.android2017.mvp.model;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zhiwenyan.cmccaifu.com.android2017.mvp.BookListener;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.BookSearchResponse;
import zhiwenyan.cmccaifu.com.android2017.retrofit.service.BookService;

/**
 * Created by zhiwenyan on 4/20/17.
 */

public class BookModel implements IBookModel {
    private static final String BASE_URL = "https://api.douban.com/v2/";
    private BookService mBookService;

    public BookModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBookService = retrofit.create(BookService.class);
    }

    @Override
    public void loadBook(final BookListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("q", "三国");
        map.put("tag", "");
        map.put("start", "0");
        map.put("count", "3");
        Call<BookSearchResponse> call = mBookService.getSearchMapBooks(map);
        call.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                listener.setBookListener(response.body());
            }

            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {

            }
        });
    }
}
