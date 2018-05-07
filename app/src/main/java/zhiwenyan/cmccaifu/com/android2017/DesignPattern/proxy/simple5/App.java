package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple5;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple4.IUserDao;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple4.UserDao;

/**
 * Description:
 * Data：4/27/2018-11:13 AM
 *
 * @author yanzhiwen
 */
public class App {
    public static void main(String[] args) {
        // 目标对象
        IUserDao target = new UserDao();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}
