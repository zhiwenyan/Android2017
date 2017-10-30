package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation;


import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by zhienyan on 29/10/2017.
 * <p>
 * 可以直接拿过来直接使用的
 */

public class NavigationBar extends AbsNavigationBar {

    protected NavigationBar(Builder builder) {
        super(builder);
    }

    /**
     * 导航栏的Builder
     */
    public static class Builder extends AbsNavigationBar.Builder<NavigationBar.Builder> {

        public Builder(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        @Override
        public NavigationBar create() {
            return new NavigationBar(this);
        }
    }
}
