package zhiwenyan.cmccaifu.com.android2017.ViewGroup.slideMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 6/10/17.
 * <p>
 * 侧滑菜单
 */

public class SlideMenu extends HorizontalScrollView {
    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;
    private GestureDetector mGestureDetector;
    private boolean mMenuIsOpen;
    private ImageView mShadowView;

    public SlideMenu(Context context) {
        this(context, null);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.slideMenu);
        float rightPadding = (int) typedArray.getDimension(R.styleable.slideMenu_rightPadding, dip2px(50));
        mMenuWidth = (int) (getScreenWidth() - rightPadding);
        typedArray.recycle();
        //手势类监听的回调
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (Math.abs(velocityY) > Math.abs(velocityX)) {
                    return false;
                }
                if (mMenuIsOpen) {
                    if (velocityX < 0) {
                        toggleMenu();
                        return true;
                    }
                } else {
                    //向右边滑动---》velocityX>0
                    if (velocityX > 0) {
                        toggleMenu();
                        return true;
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    private void toggleMenu() {
        if (mMenuIsOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup container = (ViewGroup) getChildAt(0);
        mMenuView = container.getChildAt(0);
        //内容的宽度=屏幕的宽度
        mContentView = container.getChildAt(1);
        mContentView.getLayoutParams().width = getScreenWidth();
        //指定菜单的宽度=屏幕的宽度-50dp
        mMenuView.getLayoutParams().width = mMenuWidth;
        //移除原来的View
        View oldContentView = container.getChildAt(1);
        container.removeView(oldContentView);

        FrameLayout newContentView = new FrameLayout(getContext());
        newContentView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        newContentView.addView(oldContentView);
        mShadowView = new ImageView(getContext());
        mShadowView.setBackgroundColor(Color.parseColor("#99000000"));
        newContentView.addView(mShadowView);
        container.addView(newContentView);
        newContentView.getLayoutParams().width = getScreenWidth();

    }

    //获取屏幕宽度
    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    //px->dip
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                getResources().getDisplayMetrics());
    }

    //默认关闭
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //摆放子布局执行的方法
        //默认是关闭的状态，让其自己滚动一段距离
        if (changed) {
            scrollTo(mMenuWidth, 0);
        }
    }

    /**
     * @param l    scrollX mMenuWidth ->0
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mMenuView.setTranslationX(l * 0.8f);
        //滑动的不同的位置改变其阴影效果
        float scale = l * 1f / mMenuWidth;
        mShadowView.setAlpha(1 - scale);
    }

    //处理onTouch事件处理
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mGestureDetector.onTouchEvent(ev)) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                int currentScrollX = getScrollX();
                if (currentScrollX > mMenuWidth / 2) {
                    closeMenu();
                } else {
                    openMenu();
                }
               return false;
        }
        return super.onTouchEvent(ev);
    }

    //关闭菜单
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
    }

    //打开菜单
    private void openMenu() {
        //滚动到当前的位置
        smoothScrollTo(0, 0);
        mMenuIsOpen = true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果菜单打开，手指按下的位置>menuWidth 关闭切换菜单 停止分发事件
        if (mMenuIsOpen) {
            int fingerX = (int) ev.getX();
            if (fingerX > mMenuWidth) {
                toggleMenu();
            }
            //停止分发事件
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return super.onInterceptHoverEvent(event);
    }

}
