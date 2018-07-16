package zhiwenyan.cmccaifu.com.android2017.view.multiScreenView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Description:多条目菜单筛选
 * Data：12/29/2017-2:22 PM
 *
 * @author: yanzhiwen
 */
public class ListDataScreenView extends LinearLayout implements View.OnClickListener {
    //头部的Tab
    private LinearLayout mMenuTabView;
    //
    private FrameLayout mMenuMiddleView;
    private View mShadowView;
    private Context mContext;
    private String mShadowViewColor = "#999999";
    private FrameLayout mMenuContentView;
    private BaseMenuAdapter mBaseMenuAdapter;
    //内容菜单的高度
    private int menuContentHeight;
    private int mCurrentPosition = -1;
    private boolean mExecuteAnim;

    public ListDataScreenView(Context context) {
        this(context, null);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(VERTICAL);
        initLayout();
    }

    private void initLayout() {
        //先创建一个xml布局，在加载，findViewById
        //1、创建头部存放
        mMenuTabView = new LinearLayout(mContext);
        mMenuTabView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        addView(mMenuTabView);
        //2、创建FrameLayout用来存放=阴影+菜单布局内容
        mMenuMiddleView = new FrameLayout(mContext);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        params.weight = 1;
        mMenuMiddleView.setLayoutParams(params);
        addView(mMenuMiddleView);
        //创建阴影部分 可以不设置LayoutParams，默认就是MATCH_PARENT，MATCH_PARENT
        mShadowView = new View(mContext);
        mMenuMiddleView.addView(mShadowView);
        //设置一个背景颜色
        mShadowView.setBackgroundColor(Color.parseColor(mShadowViewColor));
        //开始不显示
        mShadowView.setAlpha(0);
        mShadowView.setVisibility(GONE);
        //创建菜单 用来存放菜单内容
        mMenuContentView = new FrameLayout(mContext);
        mMenuContentView.setBackgroundColor(Color.WHITE);
        mMenuMiddleView.addView(mMenuContentView);
        mShadowView.setOnClickListener(this);

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
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (menuContentHeight == 0 && height > 0) {
            //菜单的高度75%
            menuContentHeight = (int) (height * 0.75f);
            ViewGroup.LayoutParams params = mMenuContentView.getLayoutParams();
            params.height = menuContentHeight;
            mMenuContentView.setLayoutParams(params);
            //先移上去
            mMenuContentView.setTranslationY(-menuContentHeight);
        }
    }

    /**
     * 具体的观察者的类对象
     */
    public class AdapterMenuObserver extends MenuObserver {
        @Override
        public void closeMenu() {
            //有注册就能收到通知
            ListDataScreenView.this.closeMenu();
        }
    }

    private AdapterMenuObserver mMenuObserver;

    /**
     * 设置adapter
     *
     * @param baseMenuAdapter
     */
    public void setBaseMenuAdapter(BaseMenuAdapter baseMenuAdapter) {
        if (baseMenuAdapter == null) {
            throw new NullPointerException("BaseMenuAdapter Not Null");
        }
        if (baseMenuAdapter != null && mMenuObserver != null) {
            mBaseMenuAdapter.unregisterDataSetObserver(mMenuObserver);
        }
        this.mBaseMenuAdapter = baseMenuAdapter;

        if (baseMenuAdapter != null) {
            mMenuObserver = new AdapterMenuObserver();
            mBaseMenuAdapter.registerDataSetObserver(mMenuObserver);
        }
        //获取多少条
        int count = mBaseMenuAdapter.getCount();
        for (int i = 0; i < count; i++) {
            //获取Tab
            View tabView = mBaseMenuAdapter.getView(i, mMenuTabView);
            mMenuTabView.addView(tabView);
            //设置点击事件
            setTabClick(tabView, i);
            //设置等宽
            LayoutParams params = (LayoutParams) tabView.getLayoutParams();
            params.weight = 1;
            tabView.setLayoutParams(params);
            //菜单的内容
            View menuView = mBaseMenuAdapter.getMenuView(i, mMenuContentView);
            //先不显示，点击Tab的时候打开，显示
            menuView.setVisibility(GONE);
            mMenuContentView.addView(menuView);
        }
        //内容的高度不能是全部
        //进来的时候阴影不显示，内容也是不显示（把它移上去）
        //阴影点击需要关闭菜单,打开是时候应该显示菜单内容
        //mExecuteAnim 不要影响动画效果
        //打开和关闭变化Tab的显示，肯定不能把代码写到ListDataScreenView中
    }

    private void setTabClick(final View tabView, final int position) {
        tabView.setOnClickListener(v -> {
            if (mCurrentPosition == -1) {
                //打开
                openMenu(position, tabView);
            } else {
                if (mCurrentPosition == position) {
                    closeMenu();
                } else {
                    View tabView1 = mMenuTabView.getChildAt(mCurrentPosition);
                    ((TextView) tabView1).setTextColor(Color.BLACK);

                    //切换下显示
                    View currentView = mMenuContentView.getChildAt(mCurrentPosition);
                    //会调用重新onMeasure方法
                    currentView.setVisibility(GONE);
                    mCurrentPosition = position;
                    currentView = mMenuContentView.getChildAt(mCurrentPosition);
                    currentView.setVisibility(VISIBLE);

                    tabView1 = mMenuTabView.getChildAt(mCurrentPosition);
                    ((TextView) tabView1).setTextColor(Color.RED);

                }
            }
        });

    }

    /**
     * 打开菜单
     *
     * @param position
     * @param tabView
     */
    private void openMenu(final int position, final View tabView) {
        if (mExecuteAnim) {
            return;
        }
        mShadowView.setVisibility(VISIBLE);
        //获取当前位置显示当前菜单
        View menuView = mMenuContentView.getChildAt(position);
        menuView.setVisibility(VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMenuContentView,
                "translationY", -menuContentHeight, 0);
        animator.setDuration(388);
        animator.start();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 0f, 1f);
        alphaAnimator.setDuration(388);
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mExecuteAnim = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mExecuteAnim = true;
                mBaseMenuAdapter.openMenu(position, tabView);
                mCurrentPosition = position;
            }
        });
        alphaAnimator.start();

    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        if (mExecuteAnim) {
            return;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMenuContentView,
                "translationY", 0, -menuContentHeight);
        animator.setDuration(388);
        animator.start();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 1f, 0f);
        alphaAnimator.setDuration(388);
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                View menuView = mMenuContentView.getChildAt(mCurrentPosition);
                menuView.setVisibility(GONE);
                mShadowView.setVisibility(GONE);
                mExecuteAnim = false;
                mCurrentPosition = -1;

            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mExecuteAnim = true;
                mBaseMenuAdapter.closeMenu(mMenuTabView.getChildAt(mCurrentPosition));

            }

        });
        alphaAnimator.start();

    }

    @Override
    public void onClick(View v) {
        closeMenu();
    }
}
