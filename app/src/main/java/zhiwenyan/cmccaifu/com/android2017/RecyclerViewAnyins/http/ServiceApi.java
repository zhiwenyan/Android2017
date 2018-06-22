package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.http;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by hcDarren on 2017/12/16.
 * 请求后台访问数据的 接口类
 */
public interface ServiceApi {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Call<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);
}
