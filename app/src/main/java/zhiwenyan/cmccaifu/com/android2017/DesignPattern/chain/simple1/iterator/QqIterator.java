package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1.iterator;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1.UserInfo;

/**
 * Created by yanzhiwen on 11/10/2017.
 * <p>
 * QQ用户迭代器
 */

public class QqIterator implements Iterator<UserInfo> {
    List<UserInfo> mUserInfos;
    int index = 0;

    public QqIterator(List<UserInfo> userInfos) {
        mUserInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return mUserInfos.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < mUserInfos.size();
    }
}
