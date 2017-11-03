package zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple2.pull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanzhiwen on 2017/11/3.
 * <p>
 * 微信公号 --多人订阅
 */

public class WxPublicObserverable {
    public List<IWxUser> mWxUsers;

    public WxPublicObserverable() {
        mWxUsers = new ArrayList<>();
    }

    /**
     * 订阅
     *
     * @param wxUser
     */
    public void register(IWxUser wxUser) {
        mWxUsers.add(wxUser);

    }

    /**
     * 取消订阅
     *
     * @param wxUser
     */
    public void unRegister(IWxUser wxUser) {
        mWxUsers.remove(wxUser);
    }

    public void update(String article) {
        for (IWxUser user : mWxUsers) {
            //推送文章更新
            user.read(article);
        }
    }
}
