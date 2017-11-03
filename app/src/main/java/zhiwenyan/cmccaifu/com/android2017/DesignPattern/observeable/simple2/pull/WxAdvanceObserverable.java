package zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple2.pull;

/**
 * Created by yanzhiwen on 2017/11/3.
 * <p>
 * 微信公众号
 */

public class WxAdvanceObserverable extends WxPublicObserverable {
    private String article;

    public void setArticle(String article) {
        this.article = article;
        //推送给微信用户
        update(article);
    }

    public String getArticle() {
        return article;
    }
}
