package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple1;

/**
 * Created by yanzhiwen on 2017/11/9.
 */

public class WxUserSystem {
    private UserInfo[] mUserInfos;

    public WxUserSystem() {
        mUserInfos = new UserInfo[3];
        mUserInfos[0] = (new UserInfo("Steven", "123456", "001", "M"));
        mUserInfos[1] = (new UserInfo("job", "123458", "002", "M"));
        mUserInfos[2] = (new UserInfo("Steven", "123456", "003", "W"));
    }

    public UserInfo[] getUserInfos() {
        return mUserInfos;
    }
}
