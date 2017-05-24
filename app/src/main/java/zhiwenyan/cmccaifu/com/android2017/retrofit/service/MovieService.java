package zhiwenyan.cmccaifu.com.android2017.retrofit.service;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Http.HttpResult;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.Subject;


//https://api.douban.com/v2/movie/top250?start=0&count=10

public interface MovieService {
    @GET("top250")
    Call<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
