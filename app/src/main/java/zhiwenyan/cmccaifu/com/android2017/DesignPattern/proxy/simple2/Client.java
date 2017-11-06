package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yanzhiwen on 2017/11/6.
 */

public class Client {
    public static void main(String[] args) {
        Man man = new Man("Steven");
        //返回的是IBank的实例
        IBank iBank = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader()  //ClassLoader
                , new Class<?>[]{IBank.class}    //目标接口
                , new BankInvocationHandler(man)
        );
        //当调用这个方法的时候会返回到InvocationHandler的invoke()方法
        iBank.applyBank();
        iBank.lostBank();

    }

    //动态代理InvocationHandler
    private static class BankInvocationHandler implements InvocationHandler {
        private Object mObject;

        public BankInvocationHandler(Object object) {
            mObject = object;
        }

        /**
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //执行方法  目标接口调用的方法都会到这里
            System.out.println(method.getName());
            //调用
            Object object = method.invoke(mObject, args);
            return object;
        }
    }
}
