package zhiwenyan.cmccaifu.com.android2017.banner.viewgroupbanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by yanzhiwen on 2017/9/4.
 */

public class ImageBannerViewGroup extends ViewGroup {
    public ImageBannerViewGroup(Context context) {
        this(context, null);
    }

    public ImageBannerViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageBannerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
