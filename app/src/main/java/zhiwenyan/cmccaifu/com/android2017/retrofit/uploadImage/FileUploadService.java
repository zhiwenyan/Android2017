package zhiwenyan.cmccaifu.com.android2017.retrofit.uploadImage;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * Description:
 * Data：12/12/2018-5:03 PM
 *
 * @author yanzhiwen
 */
public interface FileUploadService {
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);



    @Multipart
    @POST("you methd url upload/")
    Call<ResponseBody> uploadFile(
            @Part() RequestBody file);


    @Multipart
    @POST()
    Call<ResponseBody> uploads(
            @Url String url,
            @Part() MultipartBody.Part file);



    //上报数量确定的多张图片

    @POST("upload/")
    Call<ResponseBody> uploadFiles(@Part("filename") String description,
                                   @Part("pic\"; filename=\"image1.png") RequestBody imgs1,
                                   @Part("pic\"; filename=\"image2.png") RequestBody imgs2,
                                   @Part("pic\"; filename=\"image3.png") RequestBody imgs3,
                                   @Part("pic\"; filename=\"image4.png") RequestBody imgs4);



    //如果图片数量不确定
    @Multipart
    @POST()
    Call<ResponseBody> uploadFiles(
            @Url String url,
            @PartMap() Map<String, RequestBody> maps);

//    图文同时上报
//            Part方式
    @Multipart
    @POST("upload/")
    Call<ResponseBody> register(
            @FieldMap Map<String , String> usermaps,
            @Part("avatar\"; filename=\"avatar.jpg") RequestBody avatar);




    @Multipart
    @POST
    Call<ResponseBody> uploadFileWithPartMap(
            @Url() String url,
            @PartMap() Map<String, RequestBody> partMap,
            @Part  MultipartBody.Part file);

}