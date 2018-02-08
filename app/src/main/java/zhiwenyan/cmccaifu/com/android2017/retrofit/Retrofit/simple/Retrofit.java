package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.ServiceMethod;

/**
 * Description:
 * Data：1/19/2018-2:00 PM
 *
 * @author: yanzhiwen
 */
public class Retrofit {
    public String baseUrl;
    public okhttp3.Call.Factory callFactory;
    public Map<Method, ServiceMethod> mMethodServiceMethodMap = new ConcurrentHashMap<>();

    public Retrofit(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.callFactory = builder.callFactory;
    }

    public <T> T create(Class<T> service) {
        //检验service是不是一个接口 不能继承子接口
        //重点
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //每执行一个方法 ，都会来这里
                Log.i("TAG", "invoke: " + method.getName());
                //判断是Object的方法 ？
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }
                //解析参数注解
                ServiceMethod serviceMethod = loadServiceMethod(method);
                //2 封装okHttpCall
                OkHttpCall okHttpCall = new OkHttpCall(serviceMethod, args);
                return okHttpCall;
            }
        });
    }

    /**
     * @param method
     * @return
     */
    private ServiceMethod loadServiceMethod(Method method) {
        //享元设计模式
        ServiceMethod serviceMethod = mMethodServiceMethodMap.get(method);
        if (serviceMethod == null) {
            serviceMethod = new ServiceMethod.Builder(this, method).build();
            mMethodServiceMethodMap.put(method, serviceMethod);
        }
        return serviceMethod;

    }

    public static class Builder {
        String baseUrl;
        okhttp3.Call.Factory callFactory;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(okhttp3.Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }

        public Retrofit build() {
            if (this.callFactory == null) {
                this.callFactory = new OkHttpClient();
            }
            return new Retrofit(this);
        }
    }
}
