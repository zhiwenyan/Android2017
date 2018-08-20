package zhiwenyan.cmccaifu.com.android2017.view.refresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Description:
 * Data：8/15/2018-2:06 PM
 *
 * @author yanzhiwen
 */
public class RefreshView extends LinearLayout {
    private View mHeaderView;
    private View mBottomView;
    private View mContentView;
    private int mHeaderViewHeight;
    private int mBottomViewHeight;

    public RefreshView(Context context) {
        super(context);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = getChildAt(0);
        mContentView = getChildAt(1);
        mBottomView = getChildAt(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        mBottomViewHeight = mBottomView.getMeasuredHeight();
        if (mHeaderViewHeight > 0) {
            setHeaderViewMarginTop(-mHeaderViewHeight);
        }
        if (mBottomViewHeight > 0) {
            // setBottomViewMarginBottom(-mBottomViewHeight );
        }
    }


    private void setHeaderViewMarginTop(int marginTop) {
        MarginLayoutParams params = ( MarginLayoutParams ) mHeaderView.getLayoutParams();
        if (marginTop < -mHeaderViewHeight) {
            marginTop = -mHeaderViewHeight;
        }
        params.topMargin = marginTop;
        mHeaderView.setLayoutParams(params);
    }

    private void setBottomViewMarginBottom(int marginBottom) {
        MarginLayoutParams params = ( MarginLayoutParams ) mBottomView.getLayoutParams();

        if (marginBottom < 0) {
            marginBottom = 0;
        }
        params.bottomMargin = marginBottom;
        mBottomView.setLayoutParams(params);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;

    }

    private int mDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ( int ) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("move");
                int distanceY = ( int ) (event.getY() - mDownY);
                if (distanceY > 0) {
                    int marginTop = distanceY - mHeaderViewHeight;
                    setHeaderViewMarginTop(marginTop);
                    System.out.println("distanceY"+distanceY);
                    System.out.println("marginTop"+marginTop);
                }
                break;
        }

        return true;

    }
}
