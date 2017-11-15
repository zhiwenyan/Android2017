package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2.handler.AbsSystemHandler;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2.handler.UserInfo;

/**
 * Created by yanzhiwen on 2017/11/9.
 */

public class WxUserSystem extends AbsSystemHandler {
    private UserInfo[] mUserInfos;

    public WxUserSystem() {
        mUserInfos = new UserInfo[3];
        mUserInfos[0] = (new UserInfo("Steven", "123456", "001", "M"));
        mUserInfos[1] = (new UserInfo("job", "123458", "002", "M"));
        mUserInfos[2] = (new UserInfo("Steven", "123456", "003", "W"));
    }

    /**
     * 查询用户信息
     *
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public UserInfo queryUserInfo(String username, String pwd) {
        //自己查询一把
        for (UserInfo userInfo : mUserInfos) {
            if (userInfo.userName.equals(username) && userInfo.userPwd.equals(pwd)) {
                return userInfo;
            }
        }
        AbsSystemHandler nextHandler = getNextHandler();
        System.out.println(nextHandler);
        if (nextHandler != null) {
            return nextHandler.queryUserInfo(username, pwd);

        }
        return null;
    }


}
