package zhiwenyan.cmccaifu.com.android2017.ViewGroup.refuse;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.utils.LogUtil;

/**
 * Created by yanzhiwen on 2017/10/9.
 */

public class PtrLineaLayout extends PtrLayout {
    private Scroller mScroller;
    private View mHeaderView;
    private View mContentView;
    private int mHeaderHeight;
    private int mDownX;
    private int mDownY;
    private int mLastY;
    private int mLastX;

    public PtrLineaLayout(Context context) {
        this(context, null);
    }

    public PtrLineaLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrLineaLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1)
            throw new ArrayIndexOutOfBoundsException("PtrLinearLayout only has one child.");
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.header_view, this, false);
        addView(mHeaderView, 0);
        mContentView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHeaderHeight = getMeasuredHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.i(ev.getAction() + "==");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mLastY = (int) ev.getY();
                LogUtil.i("xiahua" + mDownY + "," + mLastY);
                if (mLastY > mDownY) {
                    //下滑
                    scrollBy(0, mLastY);
                }
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
