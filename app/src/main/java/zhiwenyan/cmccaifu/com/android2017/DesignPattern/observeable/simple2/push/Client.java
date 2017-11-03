package zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple2.push;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple2.pull.IWxUser;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple2.pull.WxUser;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple2.pull.WxAdvanceObserverable;

/**
 * Created by yanzhiwen on 2017/11/3.
 */

public class Client {
    public static void main(String[] args) {
        //微信公众号具体的被观察者
        WxAdvanceObserverable wxAdvanceObserverable = new WxAdvanceObserverable();
        //微信公众号 具体的公众号
        IWxUser user = new WxUser("Steven");
        IWxUser user1 = new WxUser("Steven1");
        wxAdvanceObserverable.register(user);
        wxAdvanceObserverable.register(user1);

        wxAdvanceObserverable.setArticle("文章");
    }
}
