package zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/10/30.
 */

public class DefaultNavigationBar extends AbsNavigationBar {


    protected DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigationBar.Builder<DefaultNavigationBar.Builder> {

        public Builder(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        @Override
        public AbsNavigationBar create() {
            return new DefaultNavigationBar(this);
        }

        public Builder setLeftText(String text) {
            setText(R.id.text, text);
            return this;
        }

        public Builder setLeftOnClickListener(View.OnClickListener listener) {
            setOnClickListener(R.id.text, listener);
            return this;
        }
    }
}
