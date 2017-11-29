package zhiwenyan.cmccaifu.com.android2017.retrofit;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Http.HttpMethods;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.BookResponse;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.BookSearchResponse;
import zhiwenyan.cmccaifu.com.android2017.retrofit.service.BookService;
import zhiwenyan.cmccaifu.com.android2017.retrofit.service.FileUploadService;

public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.resultTv)
    TextView mResultTv;
    Retrofit mRetrofit;
    private static final String BASE_URL = "https://api.douban.com/v2/";
    BookService mBookService;
    OkHttpClient mOkHttpClient;

    @Override
    protected void init() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //主要的思想在这里
        mBookService = mRetrofit.create(BookService.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit;
    }


    @OnClick({R.id.QueryBtn, R.id.QueryMapBtn, R.id.postFliedBtn, R.id.PathBtn, R.id.upload, R.id.getMovie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.QueryBtn:
                getQuery();
                break;
            case R.id.QueryMapBtn:
                getQueryMap();
                break;
            case R.id.PathBtn:
                getPath();
                break;
            case R.id.postFliedBtn:
                getPostFlied();
                break;
            case R.id.upload:
                //  upload();
                mResultTv.setText("具体的的实现请看代码");
                break;
            case R.id.getMovie:
                getMovie();
                break;
        }
    }

    private void getMovie() {
        String result = HttpMethods.getInstance().getTopMovie(0, 10);
        mResultTv.setText(result);
    }

    private void getQuery() {
        Call<BookSearchResponse> call = mBookService.getSearchBooks("小王子", "", 0, 3);
        call.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                mResultTv.setText(response.body().getBooks().get(0).getAuthor_intro());
            }

            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {
                mResultTv.setText(t.getMessage());
            }
        });
    }

    private void getQueryMap() {
        //如果Query参数比较多，那么可以通过@QueryMap方式将所有的参数集成在一个Map统一传递
        Map<String, String> map = new HashMap<>();
        map.put("q", "三国");
        map.put("tag", "");
        map.put("start", "0");
        map.put("count", "3");
        Call<BookSearchResponse> call = mBookService.getSearchMapBooks(map);
        call.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                mResultTv.setText(response.body().getBooks().get(0).getAuthor_intro());
            }

            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {
                mResultTv.setText(t.getMessage());
            }
        });
    }

    private void getPath() {
        //Call<T> 基于OKHttp的Call BookResponse 后台接口的返回值
        Call<BookResponse> call = mBookService.getBook("1003077");
        //加入队列执行
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                mResultTv.setText(response.body().getAuthor_intro());
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                mResultTv.setText(t.getMessage());
            }
        });
    }

    private void getPostFlied() {
        Call<String> call = mBookService.addReviews("1003088", "三国", "三国", "5");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mResultTv.setText(response.isSuccessful() + "");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mResultTv.setText(t.getMessage());

            }
        });
    }

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // File file = FileUtils.getFile(this, fileUri);
        File file = new File("");  //文件的路径
        // 为file建立RequestBody实例
        RequestBody requestFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        // MultipartBody.Part借助文件名完成最终的上传
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    //文件上传
    private void upload() {
        Uri file1Uri = Uri.parse(""); //获取本地的视频
        Uri file2Uri = Uri.parse(""); //获取本地的照片
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("") //上传的地址
                .build();
        // 创建上传的service实例
        FileUploadService service =
                retrofit.create(FileUploadService.class);
        // 创建文件的part (photo, video, ...)
        MultipartBody.Part body1 = prepareFilePart("video", file1Uri);
        MultipartBody.Part body2 = prepareFilePart("thumbnail", file2Uri);
        // 添加其他的part
        RequestBody description = createPartFromString("hello, this is description speaking");

        // 最后执行异步请求操作
        Call<ResponseBody> call = service.uploadMultipleFiles(description, body1, body2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }
}