package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation.navigation;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Description:
 * Data：3/21/2018-10:22 AM
 *
 * @author: yanzhiwen
 */
public class NavigationBar extends AbsNavigationBar {

    public NavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigationBar.Builder {
        public Builder(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        @Override
        public AbsNavigationBar create() {
            return new NavigationBar(this);
        }
    }
}
