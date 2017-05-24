package zhiwenyan.cmccaifu.com.android2017.retrofit.service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.BookResponse;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.BookSearchResponse;

/**
 * Created by zhiwenyan on 4/19/17.
 */


public interface BookService {
    //Get方法请求参数都会以key=value的方式拼接在url后面
    //https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
    @GET("book/search?")
    Call<BookSearchResponse> getSearchBooks(@Query("q") String name, @Query("tag") String tag,
                                            @Query("start") int start, @Query("count") int count);

    @GET("book/search?")
    Call<BookSearchResponse> getSearchMapBooks(@QueryMap Map<String, String> map);

    //https://api.douban.com/v2/book/1003077
    //如果请求的相对地址也是需要调用方传递，那么可以使用@Path注解
    @GET("book/{id}")
    Call<BookResponse> getBook(@Path("id") String id);

    //Post请求需要把请求参数放置在请求体中，而非拼接在url后面

    @FormUrlEncoded
    @POST("book/reviews")
    Call<String> addReviews(@Field("book") String bookId, @Field("title") String title,
                            @Field("content") String content, @Field("rating") String rating);
}
