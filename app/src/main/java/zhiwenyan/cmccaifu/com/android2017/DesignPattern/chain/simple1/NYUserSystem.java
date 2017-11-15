package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1.iterator.Iterator;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1.iterator.QqIterator;

/**
 * Created by yanzhiwen on 2017/11/9.
 * <p>
 * 农药系统
 */

public class NYUserSystem implements Aggregate<UserInfo> {
    private List<UserInfo> mUserInfos;

    public NYUserSystem() {
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("Steven", "123456", "001", "M"));
        mUserInfos.add(new UserInfo("job", "123458", "002", "M"));
        mUserInfos.add(new UserInfo("Tom", "123456", "003", "W"));
    }

    public NYUserSystem(List<UserInfo> userInfos) {
        mUserInfos = userInfos;
    }

    public List<UserInfo> getUserInfos() {
        return mUserInfos;
    }

    @Override
    public Iterator<UserInfo> iterator() {
        return new QqIterator(mUserInfos);
    }
}
