package zhiwenyan.cmccaifu.com.android2017.view.slideView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Description:侧滑删除View
 * Data：12/19/2017-5:25 PM
 *
 * @author: yanzhiwen
 */
public class SideDeleteView extends RelativeLayout {
    private View mContentView;
    private View mDeleteView;
    private int mDeleteViewWidth;
    private ViewDragHelper mViewDragHelper;
    private boolean mIsOpen;
    //横向移动的距离
    private int mMoveLeft;

    public SideDeleteView(Context context) {
        this(context, null);
    }

    public SideDeleteView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideDeleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return child == mContentView;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                //限制只能左滑
                if (left > 0) {
                    return 0;
                }
                //左滑动的距离最多DeleteView的宽度
                if (mDeleteViewWidth + left <= 0) {
                    return -mDeleteViewWidth;
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                return 0;
            }

            @Override
            public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                if (changedView == mContentView) {
                    mMoveLeft = left;
                }
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == mContentView) {
                    if (Math.abs(mMoveLeft) >= (mDeleteViewWidth / 2)) {
                        //设置滑动的View移动位置，即然当前的界面滑出屏幕
                        mIsOpen = true;
                        mViewDragHelper.settleCapturedViewAt(-mDeleteViewWidth, 0);
                    } else {
                        //如果没大于或等于当前界面的1/2，即恢复原来的位置
                        mViewDragHelper.settleCapturedViewAt(0, 0);
                        mIsOpen = false;
                    }
                    //通知重绘界面
                    ViewCompat.postInvalidateOnAnimation(SideDeleteView.this);
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDeleteView = getChildAt(0);
        mContentView = getChildAt(1);
    }

    /**
     * 如果删除View打开，View的事件不拦截，不打开，拦截子View的事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !mIsOpen;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChild(mDeleteView, widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mDeleteViewWidth = mDeleteView.getWidth();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    //触摸事件交给ViewDragHelper处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
