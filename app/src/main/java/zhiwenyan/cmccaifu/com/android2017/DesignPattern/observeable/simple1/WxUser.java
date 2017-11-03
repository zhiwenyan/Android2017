package zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple1;

/**
 * Created by yanzhiwen on 2017/11/3.
 * 微信公众号--订阅的用户
 */

public class WxUser implements IWxUser {
    private String name;

    public WxUser(String name) {
        this.name = name;
    }

    @Override
    public void read(String article) {
        System.out.println(name + "收到了" + article);
    }
}
