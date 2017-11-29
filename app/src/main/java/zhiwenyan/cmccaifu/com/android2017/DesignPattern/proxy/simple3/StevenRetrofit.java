package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple3;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yanzhiwen on 2017/11/6.
 */

public class StevenRetrofit {
    public <T> T create(Class<T> clazz) {
        //动态代理
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.i("TAG", "invoke: " + method.getName());

                //1、解析所有方法的注解 比如POST GET FromURLEncode 2、解析参数的的注解 Query QueryMap 等等
                //3、封装成Call<T> 对象 4、返回的对象是Call<T>
                return "返回";
            }
        });
    }

}
