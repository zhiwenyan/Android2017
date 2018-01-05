package zhiwenyan.cmccaifu.com.android2017.view.radar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Description:
 * Data：1/5/2018-4:05 PM
 *
 * @author: yanzhiwen
 */
public class RadarViewGroup extends ViewGroup {
    public RadarViewGroup(Context context) {
        super(context);
    }

    public RadarViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadarViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
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
