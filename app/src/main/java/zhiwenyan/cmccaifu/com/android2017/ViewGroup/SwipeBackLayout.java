package zhiwenyan.cmccaifu.com.android2017.ViewGroup;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


public class SwipeBackLayout extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private callBackListener mCallBackListener;
    private View mContentView;
    private int mContentWidth;
    private boolean isClose; //是否滑到了1/2的距离
    private int mMoveLeft;  //横向移动的距离

    public SwipeBackLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mContentView;
            }

            //设置水平拖动的距离
            @Override
            public int getViewHorizontalDragRange(View child) {
                //因为我们移动的是整个界面，所以直接返回整个界面的宽度就可以了
                //   return child == mContentView ? mContentWidth : 0; //解决冲突：如果触摸是mContentView，则返回mContentWidth，
                return mContentWidth;
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return 0;
            }

            //值得变化
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if (changedView == mContentView) {
                    //记录左边的值的变化，因为我们实现的是往右滑动，所以只记录左边的值就可以了
                    mMoveLeft = left;
                    //mMoveLeft==mContentWidth如果滑动的距离正好等于mContentWidth
                    if (isClose || mMoveLeft == mContentWidth) {
                        //也就是说当前的界面已经滑出屏幕，就回调finish方法，通知activity可以finish了
                        if (mCallBackListener != null) {
                            mCallBackListener.finish();
                        }
                    }
                }
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                //水平移动距离的范围是0~当前界面的宽度，如果left小于0直接返回0，
                // 如果大于当前界面的宽度直接返回当前界面宽度
                //也就是控制当前界面只能往右移动
                return Math.min(mContentWidth, Math.max(left, 0));
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //如果移动的距离大于或等于当前界面的1/2，则触发关闭
                if (mMoveLeft >= (mContentWidth / 2)) {
                    isClose = true;
                    //设置滑动的View移动位置，即然当前的界面滑出屏幕
                    mViewDragHelper.settleCapturedViewAt(mContentWidth, releasedChild.getTop());
                } else {
                    //如果没大于或等于当前界面的1/2，即恢复原来的位置
                    mViewDragHelper.settleCapturedViewAt(0, releasedChild.getTop());
                }
                //通知重绘界面
                ViewCompat.postInvalidateOnAnimation(SwipeBackLayout.this);
            }
        });
    }

    //xml文件加载完之后调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取View
        mContentView = this.getChildAt(0);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mContentWidth = mContentView.getWidth();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    //触摸事件交给ViewDragHelper处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    public void setCallBackListener(callBackListener callBackListener) {
        this.mCallBackListener = callBackListener;
    }

    //接口：当滑动到1/2时回调改接口
    public interface callBackListener {
        void finish();
    }
}
