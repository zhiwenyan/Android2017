package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Request;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.Http.GET;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.Http.POST;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.Http.Query;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple.RequestBuilder;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple.Retrofit;

/**
 * Description: 解析注解
 * Data：1/19/2018-3:12 PM
 *
 * @author: yanzhiwen
 */
public class ServiceMethod {
    final Retrofit mRetrofit;
    final Method mMethod;
    final Annotation[] mAnnotations;
    final Annotation[][] parameterAnnotations;
    String httpMethod;
    String relativeUrl;
    ParameterHandler<?>[] mParameterHandlers;

    public ServiceMethod(Builder builder) {
        this.mRetrofit = builder.mRetrofit;
        this.mMethod = builder.mMethod;
        this.mAnnotations = builder.mAnnotations;
        this.parameterAnnotations = builder.parameterAnnotations;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.mParameterHandlers = builder.mParameterHandlers;

    }

    public okhttp3.Call createNewCall(Object[] args) {
        RequestBuilder requestBuilder=new RequestBuilder(mRetrofit.baseUrl,relativeUrl,httpMethod,mParameterHandlers,args);
        Request.Builder builder = new Request.Builder();
        String url = mRetrofit.baseUrl + relativeUrl;
        //参数在哪里？ ParameterHandlers
        for (ParameterHandler<?> parameterHandler:mParameterHandlers){
          //  parameterHandler.apply();
        }
        return mRetrofit.callFactory.newCall(builder.build());
    }

    public static class Builder {
        final Retrofit mRetrofit;
        final Method mMethod;
        final Annotation[] mAnnotations;
        final Annotation[][] parameterAnnotations;
        String httpMethod;
        String relativeUrl;
        ParameterHandler<?>[] mParameterHandlers;

        public Builder(Retrofit retrofit, Method method) {
            this.mRetrofit = retrofit;
            this.mMethod = method;
            mAnnotations = method.getAnnotations();
            //参数注解 二位数组
            parameterAnnotations = method.getParameterAnnotations();
            mParameterHandlers = new ParameterHandler[parameterAnnotations.length];
        }

        public ServiceMethod build() {
            //解析参数 OKhttp请求的时候 url，Baseurl+relativeUrl,method
            for (Annotation methodAnimation : mAnnotations) {
                //解析GET POST
                parseAnnotation(methodAnimation);
            }
            int count = mParameterHandlers.length;
            for (int i = 0; i < count; i++) {
                Annotation parameter = parameterAnnotations[i][0];
                //@Query 等等
                Log.i("TAG", "parameter==" + parameter.annotationType().getName());
                //会设计到模板设计模式和策略设计模式
                if (parameter instanceof Query) {
                    //一个一个封装成mParameterHandler，不同参数注解选择不同的策略
                    mParameterHandlers[i] = new ParameterHandler.Query<>(((Query) parameter).value());
                }
            }
            return new ServiceMethod(this);
        }

        private void parseAnnotation(Annotation methodAnimation) {
            if (methodAnimation instanceof GET) {
                parseMethodAndPath("GET", ((GET) methodAnimation).value());
            } else if (methodAnimation instanceof POST) {
                parseMethodAndPath("POST", ((POST) methodAnimation).value());
            }
        }

        private void parseMethodAndPath(String method, String value) {
            this.httpMethod = method;
            this.relativeUrl = value;
        }
    }
}
