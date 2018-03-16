package zhiwenyan.cmccaifu.com.android2017.view.jianshu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Description:
 * Data：3/15/2018-1:49 PM
 *
 * @author: yanzhiwen
 */
public class JianshuCircleView extends LinearLayout {

    public JianshuCircleView(Context context) {
        super(context);
    }

    public JianshuCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JianshuCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int left = 0, top = 0, right = 0, bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            left += childView.getWidth() / 3;
            childView.layout(left, top, right, bottom);
        }
    }

    public void addCircleView(MultiShapeView multiShapeView) {
        addView(multiShapeView);
    }
}
