package zhiwenyan.cmccaifu.com.android2017.ViewGroup.Drag;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zhiwenyan on 08/07/2017.
 * <p>
 * 折叠式菜单
 *
 *
 * ViewDragHelper  实现卡片拖动效果
 */

public class VerticalDragListView extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mMenuView;
    private View mDragListView;
    private int mMenuHeight;
    private boolean mIsMenuOpen = false;  //菜单是否打开


    public VerticalDragListView(@NonNull Context context) {
        this(context, null);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, mCallback);
    }

    //RecycleView向下滑动拦截
    private float mDownY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //菜单打开拦截
        if (mIsMenuOpen) {
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                mViewDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                //向下滑动&&滚动到了顶部拦截，不让RecycleView做处理
                if (moveY - mDownY > 0 && !canChildScrollUp()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //判断View是否滚动了顶部
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mDragListView instanceof RecyclerView) {
                final RecyclerView recyclerView = (RecyclerView) mDragListView;
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                return recyclerView.getChildCount() > 0 && (manager.findFirstVisibleItemPosition() > 0
                        || recyclerView.getChildAt(0).getTop() < recyclerView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mDragListView, -1) || mDragListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mDragListView, -1);
        }
    }


    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == mDragListView;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            if (top <= 0) {
                top = 0;
            }
            if (top >= mMenuHeight) {
                top = mMenuHeight;
            }
            return top;
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == mDragListView) {
                if (mDragListView.getTop() > mMenuHeight / 2) {
                    mViewDragHelper.settleCapturedViewAt(0, mMenuHeight); //打开
                    mIsMenuOpen = true;
                } else {
                    mViewDragHelper.settleCapturedViewAt(0, 0);  //关闭
                    mIsMenuOpen = false;
                }
                invalidate();
            }
        }

    };


    //响应滚动
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("VerticalDragListView 只能包含两个子布局");
        }
        mDragListView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("TAG", "onLayout: " + changed);
        if (changed) {
            View menuView = getChildAt(0);
            mMenuHeight = menuView.getMeasuredHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
