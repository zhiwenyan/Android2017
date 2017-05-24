package zhiwenyan.cmccaifu.com.android2017.ViewGroup;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/*

 ViewDragHelper还能做以下的一些操作：
 边界检测、加速度检测(eg：DrawerLayout边界触发拉出)
 回调Drag Release（eg：DrawerLayout部分，手指抬起，自动展开/收缩）
 移动到某个指定的位置(eg:点击Button，展开/关闭Drawerlayout)

 */

public class VHLayout extends LinearLayout {
    private ViewDragHelper mViewDragHelper;
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;
    private Point mAutoBackOriginPos = new Point();
    private int mScreenWidth;
    private int mScreenHeight;


    public VHLayout(Context context) {
        this(context, null);
    }

    public VHLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VHLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1f, new ViewDragHelper.Callback() {
            //捕捉那个View
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDragView || child == mAutoBackView || child == mEdgeTrackerView;

            }

            //mEdgeTrackerView边界处理
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (child == mEdgeTrackerView) {
                    if (left <= 0) {
                        return 0;
                    } else if (left >= mScreenWidth - child.getWidth()) {
                        return mScreenWidth - child.getWidth();
                    }
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (child == mEdgeTrackerView) {
                    if (top <= 0) {
                        return 0;
                    } else if (top >= mScreenHeight - child.getHeight()) {
                        return mScreenHeight - child.getHeight();
                    }
                }
                return top;
            }

            //手指释放后回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (releasedChild == mAutoBackView) {
                    //恢复到最初的位置
                    mViewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    invalidate(); //刷新

                }
            }

            //在边界拖动的时候回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
                //  mViewDragHelper.captureChildView(mEdgeTrackerView, pointerId);
            }
        });
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    //XMl布局文件加载完之后调用改方法
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }
}
