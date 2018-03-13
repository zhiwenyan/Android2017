package zhiwenyan.cmccaifu.com.android2017.view.KuoguMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/11/7.
 */

public class KugouMenu extends HorizontalScrollView {
    private ViewGroup mContainerView;
    private View mMenuView;
    private View mContentView;
    private int mMenuMarginRight;
    private int mMenuWidth;
    //手势处理类
    private GestureDetector mGestureDetector;
    private boolean mIsMenuOpen;
    private boolean mIntercept = false;

    public KugouMenu(Context context) {
        this(context, null);
    }

    public KugouMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KugouMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.KugouMenu);
        mMenuMarginRight = (int) typedArray.getDimension(R.styleable.KugouMenu_menuMarginRight,
                ScreenUtils.dip2px(context, 60));
        typedArray.recycle();
        //菜单的宽度
        mMenuWidth = ScreenUtils.getScreenWidth(context) - mMenuMarginRight;

        /**
         * 快速滑动可以切换
         */
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("TAG", "onFling: velocityX-->" + velocityX);
                //往左边滑动大于0  右边滑动小于0
                if (mIsMenuOpen) {
                    if (velocityX < 0) {
                        closeMenu();
                        return true;
                    }
                } else {
                    if (velocityX > 0) {
                        openMenu();
                        return true;

                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    /**
     * 解析xml布局之后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContainerView = (ViewGroup) getChildAt(0);
        if (mContainerView.getChildCount() != 2) {
            throw new RuntimeException("只能两个子View");
        }
        //菜单View
        mMenuView = mContainerView.getChildAt(0);

        ViewGroup.LayoutParams menuParams = mMenuView.getLayoutParams();
        menuParams.width = mMenuWidth;
        //7.0以下手机必须设置
        mMenuView.setLayoutParams(menuParams);
        //内容View
        mContentView = mContainerView.getChildAt(1);

        ViewGroup.LayoutParams contentParams = mContentView.getLayoutParams();
        contentParams.width = ScreenUtils.getScreenWidth(getContext());
        //7.0以下手机必须设置
        mContentView.setLayoutParams(contentParams);
        //在onLayout()之前调用
        //scrollTo(mMenuWidth, 0);

        //处理菜单内容的缩放和透明度
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("TAG", "onScrollChanged: " + "l=" + l + ",t=" + t + ",oldl=" + oldl + ",oldt=" + oldt);
        //算一个梯度值
        float scale = 1f * l / mMenuWidth;
        Log.i("TAG", "onScrollChanged: scale==" + scale);
        float rightScale = 0.7f + 0.3f * scale;   //(scale)1->0
        //默认是以中心缩放
        //设置缩放点
        ViewCompat.setPivotX(mContentView, 0);
        ViewCompat.setPivotY(mContentView, mContentView.getMeasuredHeight() / 2);
        ViewCompat.setScaleX(mContentView, rightScale);
        ViewCompat.setScaleY(mContentView, rightScale);

        //菜单的设置 缩放和透明度
        float alpha = 0.7f + 0.3f * (1 - scale);
        ViewCompat.setAlpha(mMenuView, alpha);

        float leftScale = 0.7f + 0.3f * (1 - scale);
        ViewCompat.setScaleX(mMenuView, leftScale);
        ViewCompat.setScaleY(mMenuView, leftScale);

    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 摆放
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //进来的时候菜单默认关闭
        scrollTo(mMenuWidth, 0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mIntercept = false;
        if (mIsMenuOpen) {
            float currentX = ev.getX();
            if (currentX > mMenuWidth) {
                mIntercept = true;
                //关闭菜单
                closeMenu();
                //拦截子View不需要响应任何事件
                //如果返回true，代表会拦截子View的事件，但是会响应自己的onTouchEvent事件
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mIntercept) {
            return true;
        }
        //如果快速滑动下面就不要执行了
        if (mGestureDetector.onTouchEvent(ev)) {
            return true;
        }

        //获取手指滑动的速率

        //处理事件拦截

        //当菜单打开的时候，手指触摸右边需要关闭菜单，并且需要事件拦截
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int currentScorllX = getScrollX();
                if (currentScorllX > mMenuWidth / 2) {
                    closeMenu();
                } else {
                    openMenu();
                }
                //确保super.onTouchEvent(ev)不执行
                return true;
        }
        return super.onTouchEvent(ev);
    }

    private void toggleMenu() {

    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        smoothScrollTo(0, 0);//有动画
        mIsMenuOpen = true;
    }

    /**
     * 禁止菜单
     */
    public void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mIsMenuOpen = false;

    }
}
