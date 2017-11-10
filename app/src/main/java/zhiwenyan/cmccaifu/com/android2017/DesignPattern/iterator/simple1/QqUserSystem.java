package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanzhiwen on 2017/11/9.
 */

public class QqUserSystem {
    private List<UserInfo> mUserInfos;

    public QqUserSystem() {
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("Steven", "123456", "001", "M"));
        mUserInfos.add(new UserInfo("job", "123458", "002", "M"));
        mUserInfos.add(new UserInfo("Tom", "123456", "003", "W"));
    }

    public QqUserSystem(List<UserInfo> userInfos) {
        mUserInfos = userInfos;
    }

    public List<UserInfo> getUserInfos() {
        return mUserInfos;
    }
}
