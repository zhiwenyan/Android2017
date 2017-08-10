package zhiwenyan.cmccaifu.com.android2017.animator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 2017/8/10.
 */

public class LoadingView extends LinearLayout {
    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        //传this把这个布局加载中LinearLayout中
        inflate(getContext(), R.layout.wuba_loading, this);
    }
}
