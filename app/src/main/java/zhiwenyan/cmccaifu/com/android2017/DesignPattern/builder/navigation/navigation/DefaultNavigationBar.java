package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation.navigation;

import android.content.Context;
import android.view.ViewGroup;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：3/21/2018-10:55 AM
 *
 * @author: yanzhiwen
 */
public class DefaultNavigationBar extends AbsNavigationBar {
    public DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigationBar.Builder {
        public Builder(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        @Override
        public AbsNavigationBar create() {
            return new DefaultNavigationBar(this);
        }

        public Builder setLeftText(String text) {
            setText(R.id.textView, text);
            return this;
        }
    }
}
