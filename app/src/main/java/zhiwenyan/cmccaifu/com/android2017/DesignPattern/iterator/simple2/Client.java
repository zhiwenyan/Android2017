package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple2;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple2.iterator.Iterator;

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
        //两个系统放到同一个地方
        UserInfo userInfo = queryWxUserInfo("Steven", "123456", wxUserSystem.iterator());
        //这个地方做了if else 判断
        if (userInfo == null) {
            userInfo = queryQqUserInfo("Steven", "123456", qqUserSystem.iterator());
        }
        System.out.println(userInfo.toString());
    }

    /**
     * @param steven
     * @param s
     * @param qqUserSystem
     * @return
     */
    private static UserInfo queryQqUserInfo(String steven, String s, Iterator<UserInfo> qqUserSystem) {
        while (qqUserSystem.hasNext()) {
            UserInfo userInfo = qqUserSystem.next();
            if (userInfo.userName.equals(steven) && userInfo.userPwd.equals(s)) {
                return userInfo;
            }
        }
        return null;
    }

    /**
     * @param steven
     * @param s
     * @param wxUserSystem
     * @return
     */
    private static UserInfo queryWxUserInfo(String steven, String s, Iterator<UserInfo> wxUserSystem) {
        while (wxUserSystem.hasNext()) {
            UserInfo userInfo = wxUserSystem.next();
            if (userInfo.userName.equals(steven) && userInfo.userPwd.equals(s)) {
                return userInfo;
            }
        }
        return null;
    }
}
