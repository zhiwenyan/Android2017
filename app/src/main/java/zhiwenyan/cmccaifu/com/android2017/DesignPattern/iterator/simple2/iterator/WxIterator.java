package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple2.iterator;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple2.UserInfo;

/**
 * Created by yanzhiwen on 11/10/2017.
 * <p>
 * 微信用户迭代器
 */

public class WxIterator implements Iterator<UserInfo> {
    UserInfo[] mUserInfos;
    int index = 0;

    public WxIterator(UserInfo[] userInfos) {
        mUserInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return mUserInfos[index++];
    }

    @Override
    public boolean hasNext() {
        return index < mUserInfos.length;
    }
}
