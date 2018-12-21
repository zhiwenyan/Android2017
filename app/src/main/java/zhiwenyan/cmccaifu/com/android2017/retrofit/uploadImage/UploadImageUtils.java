package zhiwenyan.cmccaifu.com.android2017.retrofit.uploadImage;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description:
 * Data：12/12/2018-5:06 PM
 *
 * @author yanzhiwen
 */
public class UploadImageUtils {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public static void uploadFile(File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        // 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        // 执行请求
        Call<ResponseBody> call = RetrofitClient.getServiceApi().upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });

    }

    public static void uploadFiles(String url, File[] files) {
        Map<String, RequestBody> map = new HashMap<>();
        for (File file : files) {
            MultipartBody multipartBody = new MultipartBody.Builder().
                    addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file))
                    .build();
            map.put("", multipartBody);
        }

        RetrofitClient.getServiceApi().uploadFiles(url, map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void uploads(String url, File file) {
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
        RetrofitClient.getServiceApi().uploads(url, part).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
