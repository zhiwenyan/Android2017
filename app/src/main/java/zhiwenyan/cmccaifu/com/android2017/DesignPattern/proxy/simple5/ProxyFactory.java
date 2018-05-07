package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple5;

import java.lang.reflect.Proxy;

/**
 * Description:
 * 动态代理有以下特点:
 1.代理对象,不需要实现接口
 2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
 3.动态代理也叫做:JDK代理,接口代理
 JDK中生成代理对象的API
 代理类所在包:java.lang.reflect.Proxy
 * 创建动态代理对象
 * 动态代理不需要实现接口,但是需要指定接口类型
 * Data：4/27/2018-11:13 AM
 *
 * @author yanzhiwen
 */
public class ProxyFactory {

    //维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance() {
        /**
         ClassLoader loader,:指定当前目标对象使用类加载器,获取加载器的方法是固定的
         Class<?>[] interfaces,:目标对象实现的接口的类型,使用泛型方式确认类型
         InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
         */
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("开始事务2");
                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("提交事务2");
                    return returnValue;
                }
        );
    }

}