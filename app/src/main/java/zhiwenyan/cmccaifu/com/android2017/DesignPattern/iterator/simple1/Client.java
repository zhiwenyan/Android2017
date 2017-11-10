package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple1;

import java.util.List;

/**
 * Created by yanzhiwen on 2017/11/9.
 */

public class Client {
    public static void main(String[] args) {
        //根据用户名和密码去去查询信息
        //如果没有查询到 则代表登录失败  查询到了 登录成功

        UserInfo userInfo = queryWxUserInfo("Steven", "123456");

        if (userInfo == null) {
            userInfo = queryQqUserInfo("Steven", "123456");

        }
        System.out.println(userInfo.toString());
    }

    private static UserInfo queryWxUserInfo(String steven, String s) {
        WxUserSystem wxUserSystem = new WxUserSystem();
        UserInfo[] userInfos = wxUserSystem.getUserInfos();
        for (int i = 0; i < userInfos.length; i++) {
            if (userInfos[i].userName.equals(steven) && userInfos[i].userPwd.equals(s)) {
                return userInfos[i];
            }
        }
        return null;

    }

    private static UserInfo queryQqUserInfo(String steven, String s) {
        QqUserSystem qqUserSystem = new QqUserSystem();
        List<UserInfo> list = qqUserSystem.getUserInfos();
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = list.get(i);
            if (userInfo.userName.equals(steven) && userInfo.userPwd.equals(s)) {
                return userInfo;

            }
        }
        return null;
    }
}
