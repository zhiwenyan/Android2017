package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.bottomTab;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 11/10/2017.
 */

public class MainBottomTabItem extends BottomTabItem {
    private Builder mBuilder;

    private MainBottomTabItem(Context context) {
        super(R.layout.tab_item, context);
    }

    public MainBottomTabItem(Builder builder) {
        this(builder.mContext);
        mBuilder = builder;
    }

    @Override
    protected void setSelected(boolean selected) {
        android.widget.TextView tabText = findViewById(R.id.text);
        ImageView tabIcon = findViewById(R.id.iconId);

        tabText.setSelected(selected);
        tabIcon.setSelected(selected);
    }

    @Override
    protected void initLayout() {
        //初始化显示
        android.widget.TextView tabText = findViewById(R.id.text);
        ImageView tabIcon = findViewById(R.id.iconId);

        if (!TextUtils.isEmpty(mBuilder.mText)) {
            tabText.setText(mBuilder.mText);
        }
        if (mBuilder.mIconId != 0) {
            tabIcon.setImageResource(mBuilder.mIconId);
        }

    }

    public static class Builder {
        Context mContext;
        String mText;
        int mIconId;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setText(String text) {
            this.mText = text;
            return this;
        }

        public Builder setIcon(int mIconId) {
            this.mIconId = mIconId;
            return this;
        }

        public MainBottomTabItem create() {
            return new MainBottomTabItem(this);
        }

    }
}
