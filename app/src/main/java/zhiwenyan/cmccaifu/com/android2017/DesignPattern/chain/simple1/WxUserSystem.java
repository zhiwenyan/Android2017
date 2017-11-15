package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1.iterator.Iterator;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1.iterator.WxIterator;

/**
 * Created by yanzhiwen on 2017/11/9.
 */

public class WxUserSystem implements Aggregate<UserInfo> {
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

    @Override
    public Iterator<UserInfo> iterator() {
        return new WxIterator(mUserInfos);
    }
}
