package zhiwenyan.cmccaifu.com.android2017.Http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zhiwenyan.cmccaifu.com.android2017.BasicApp;
import zhiwenyan.cmccaifu.com.android2017.utils.LogUtil;
import zhiwenyan.cmccaifu.com.android2017.utils.NetworkUtils;

import static com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner;

/**
 * 管理所有请求类
 */
public class OkHttpManager {
    private static final String SUCCESS_CODE = "200";
    private static final String FAIL_CODE = "401";
    private static final String ERROR = "code";
    private static final String RESULTS = "result";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private static final String TAG = OkHttpManager.class.getSimpleName();
    private static String AUTH_HEADER_VALUE = "";

    public static void setAuthorizationCode(String authCode) {
        if (TextUtils.isEmpty(authCode)) {
            AUTH_HEADER_VALUE = "";
        } else {
            AUTH_HEADER_VALUE = authCode;
        }
    }

    private OkHttpManager() {
        this.mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new TokenInterceptor(AUTH_HEADER_VALUE))
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .build();
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpManager getInstance() {
        return SingleFactory.manger;
    }

    private static final class SingleFactory {
        private static final OkHttpManager manger = new OkHttpManager();
    }

    /**
     * get请求
     *
     * @param tag      标志
     * @param url      请求的地址
     * @param callBack 回调接口
     */
    public void get(String tag, final String url, final HttpCallBack<? extends Object> callBack) {
        if (!NetworkUtils.isConnectedByState(BasicApp.getContext())) {
            callBack.onFailure(5, "主人，请检查您的网络！");
            return;
        }
        if (!NetworkUtils.isNetAvailable(BasicApp.getContext())) {
            callBack.onFailure(5, "主人，当前网络不可用！");
            return;
        }
        //初始化请求对象
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();

        //服务器发送异步请求
        //队列处理  enqueue
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                if (!call.isCanceled()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailure(4, e.getLocalizedMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    //获取String类型响应，注意是string(),不是toString()
                    final String json = response.body().string();
                    //在控制台格式化打印json数据
                    LogUtil.i(json);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleResponse(json, callBack);
                        }
                    });

                }
            }
        });
    }

    /**
     * 处理请求结果
     *
     * @param json     数据
     * @param callBack 回调接口
     */
    private void handleResponse(String json, HttpCallBack callBack) {
        try {
            //转化为json对象
            JSONObject jsonObject = new JSONObject(json);
            //判断error字段是否存在，存在返回失败信息并结束请求
            if (!jsonObject.isNull(ERROR)) {
                //将错误信息返回
                callBack.onFailure(0, jsonObject.getString(ERROR));
                return;
            }
            //判断results字段是否存在，不存在返回时报回调并结束请求
            if (jsonObject.isNull(RESULTS)) {
                callBack.onFailure(0, "results key not exists!!");
                return;
            }
            //获取results的值
            String results = jsonObject.getString(RESULTS);
            //返回成功回调
            callBack.onSuccess(new Gson().fromJson(json, callBack.type));
        } catch (JSONException e) {
            callBack.onFailure(0, e.getLocalizedMessage());
        }
    }

    /**
     * 获取列表数据请求
     *
     * @param tag      标签，唯一标识
     * @param url
     * @param type
     * @param listener
     */
    public void getList(String tag, final String url, final Type type, final HttpListener listener) {

        if (!NetworkUtils.isConnectedByState(BasicApp.getContext())) {
            listener.onFailure(5, "主人，请检查您的网络！！");
            return;
        }
        if (!NetworkUtils.isNetAvailable(BasicApp.getContext())) {
            listener.onFailure(5, "主人，当前网络不可用！");
            return;
        }

        //构建一个请求
        Request request = new Request.Builder()
                .tag(tag)
                .url(url)
                .build();
        //newCall() 把Request转化成RealCall
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                if (!call.isCanceled()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onFailure(4, e.getLocalizedMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                String authorization = response.header("Authorization");
//                if (authorization != null) {
//                    PreferencesUtils.putString(CashMall.getContext(), "Authorization", "Beare " + authorization);
//                }
                final String finalJson = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Logger.i(finalJson);
                            //转化为json对象
                            JSONObject jsonObject = new JSONObject(finalJson);
                            //判断error字段是否存在，存在返回失败信息并结束请求
//                            if (!jsonObject.isNull(ERROR)) {
//                                listener.onFailure(0, "error key not exists!!");
//                                return;
//                            }
                            //判断results字段是否存在，不存在返回时报回调并结束请求
                            if (jsonObject.isNull(RESULTS)) {
                                listener.onFailure(0, "results key not exists!!");
                                return;
                            }
                            //获取results的值
                            String results = jsonObject.getString(RESULTS);

                            LogUtil.i(results);
                            //返回成功回调
                            Object o = new Gson().fromJson(results, newParameterizedTypeWithOwner(null, ArrayList.class, type));
                            listener.onSuccess(o);

                        } catch (JSONException e) {
                            listener.onFailure(0, e.getLocalizedMessage());
                        }
                    }
                });
            }
        });
    }


    /**
     * post map异步请求,键值对
     *
     * @param url      :地址
     * @param params   :参数
     * @param callback :回调
     */

    public void post(String url, HttpCallBack<String> callback, Map<String, Object> params) throws IOException {
        if (netWorkError(callback)) {
            return;
        }
        Call call = mOkHttpClient.newCall(buildPostRequest(url, params));
        deliveryResult(call, callback);
    }


    /**
     * post json异步请求,键值对
     *
     * @param url      :地址
     * @param json     :json
     * @param callback :回调
     */

    public void post(String tag, String url, final HttpCallBack<String> callback, String json) {
        if (netWorkError(callback)) {
            return;
        }
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        deliveryResult(call, callback);
    }

    public void put(String url, HttpCallBack<String> callback, Map<String, Object> params) throws IOException {
        if (netWorkError(callback)) {
            return;
        }
        Call call = mOkHttpClient.newCall(buildPutRequest(url, params));
        deliveryResult(call, callback);
    }


    /**
     * 数据请求并处理
     *
     * @param call
     * @param callback
     */
    private void deliveryResult(Call call, final HttpCallBack<String> callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                if (!call.isCanceled()) {
                    LogUtil.e(e.getMessage());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure(-1, e.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    LogUtil.i(result);
                    JSONObject json;
                    try {
                        json = new JSONObject(result);
                    } catch (JSONException e) {
                        return;
                    }
                    //返回200说明请求数据成功
                    String code = json.optString("code");
                    LogUtil.i("message" + json.optString("message"));
                    if (code.equals(SUCCESS_CODE)) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result);
                            }
                        });
                        //返回401 请求数据失败
                    } else if (code.equals(FAIL_CODE)) {
                        final String errorResult = json.optString("message");
                        LogUtil.i("message" + errorResult);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure(-1, errorResult);
                            }
                        });
                    }

                }
            }
        });
    }

    /**
     * 生成request
     *
     * @param url
     * @param params
     * @return
     */
    private Request buildPostRequest(String url, Map<String, Object> params) {
        Request request = null;
        if (params != null) {
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : entries) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }
            request = new Request.Builder().url(url).post(builder.build()).build();
        }
        return request;
    }

    /**
     * 生成request
     *
     * @param url
     * @param params
     * @return
     */
    private Request buildPutRequest(String url, Map<String, Object> params) {
        Request request = null;
        if (params != null) {
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : entries) {
                builder.add(entry.getKey(), entry.getValue().toString());
            }
            request = new Request.Builder().url(url).put(builder.build()).build();
        }
        return request;
    }

    /**
     * 根据tag取消请求
     *
     * @param tag 标签
     */
    public void cancelRequest(String tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
    }

    /**
     * 请求响应日志信息，方便debug
     */
    private final class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.d(TAG, String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d(TAG, String.format("Received response for %s in %.1fms%n%sconnection=%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers(), chain.connection()));

            return response;
        }
    }

    private final class TokenInterceptor implements Interceptor {
        private static final String USER_TOKEN = "Authorization";
        private final String token;

        public TokenInterceptor(String token) {
            this.token = token;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            if (token == null || originalRequest.header("Authorization") != null) {
                return chain.proceed(originalRequest);
            }
            Request request = originalRequest.newBuilder()
                    .header(USER_TOKEN, token)
                    .build();
            return chain.proceed(request);
        }
    }

    private boolean netWorkError(HttpCallBack<String> callback) {
        if (!NetworkUtils.isConnectedByState(BasicApp.getContext())) {
            callback.onFailure(-1, "主人，请检查您的网络！！");
            return true;
        }
        if (!NetworkUtils.isNetAvailable(BasicApp.getContext())) {
            callback.onFailure(-1, "主人，当前网络不可用！");
            return true;
        }
        return false;
    }
}