package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2.handler.UserInfo;

/**
 * Created by yanzhiwen on 2017/11/9.
 * <p>
 * 一般写法 采用迭代器模式
 */

public class Client {
    public static void main(String[] args) {
        //根据用户名和密码去去查询信息
        //如果没有查询到 则代表登录失败  查询到了 登录成功
        WxUserSystem wxUserSystem = new WxUserSystem();
        QqUserSystem qqUserSystem = new QqUserSystem();
        NYUserSystem nyUserSystem = new NYUserSystem();
        wxUserSystem.nextHandler(qqUserSystem);
        qqUserSystem.nextHandler(nyUserSystem);
        UserInfo userInfo = wxUserSystem.queryUserInfo("Steven", "123456");
        System.out.println(userInfo);
    }

}
