package zhiwenyan.cmccaifu.com.android2017.view.multiView;

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

/**
 * Created by zhiwenyan on 2017/8/14.
 */

public class ListDataScreenView extends LinearLayout implements View.OnClickListener {
    private LinearLayout mTabMenuView;
    private Context mContext;
    private FrameLayout mMenuMiddleView;
    private View mShadowView;
    private int mShadowViewColor = Color.parseColor("#cecece");
    private int menuContentHeight;
    private FrameLayout mMenuContainerView;
    private BaseMenuAdapter mBaseMenuAdapter;
    private int mCurrentPosition = -1;
    private int mClosePostion;

    public ListDataScreenView(Context context) {
        this(context, null);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initLayout();
    }

    private void initLayout() {
        setOrientation(VERTICAL);
        mTabMenuView = new LinearLayout(mContext);
        //创建头部用来存放 Tab
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mTabMenuView.setLayoutParams(params);
        addView(mTabMenuView);
        // 创建 FrameLayout 用来存放 = 阴影（View） + 菜单内容布局(FrameLayout)
        mMenuMiddleView = new FrameLayout(mContext);
        LinearLayout.LayoutParams middleParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        middleParam.weight = 1;
        mMenuMiddleView.setLayoutParams(middleParam);
        //创建菜单，用来存放菜单内容
        mMenuContainerView = new FrameLayout(mContext);
        mMenuContainerView.setBackgroundColor(Color.WHITE);
        addView(mMenuContainerView);
        //创建阴影 可以不用设置 LayoutParams 默认就是 MATCH_PARENT ，MATCH_PARENT
        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(mShadowViewColor);
        //刚开始第一次ShadowView隐藏
        mShadowView.setAlpha(0f);
        mShadowView.setOnClickListener(this);
        mShadowView.setVisibility(GONE);
        mMenuMiddleView.addView(mShadowView);

        // 创建菜单用来存放菜单内容
        mMenuContainerView = new FrameLayout(mContext);
        mMenuContainerView.setBackgroundColor(Color.WHITE);
        mMenuMiddleView.addView(mMenuContainerView);

        addView(mMenuMiddleView);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //内容的高度占屏幕的高度75%·
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (menuContentHeight == 0 && height > 0) {
            menuContentHeight = (int) (height * .75f);
            ViewGroup.LayoutParams params = mMenuContainerView.getLayoutParams();
            params.height = menuContentHeight;
            mMenuContainerView.setLayoutParams(params);
            mMenuContainerView.setTranslationY(-menuContentHeight);
        }
    }


    /**
     * 设置Adapter
     *
     * @param adapter
     */
    public void setAdapter(BaseMenuAdapter adapter) {
        this.mBaseMenuAdapter = adapter;
        //获取有多少条
        int count = mBaseMenuAdapter.getCount();
        for (int i = 0; i < count; i++) {
            //菜单的tabView
            View tabView = mBaseMenuAdapter.getTabView(i, mTabMenuView);
            mTabMenuView.addView(tabView);
            LinearLayout.LayoutParams params = (LayoutParams) tabView.getLayoutParams();
            params.weight = 1;
            tabView.setLayoutParams(params);
            setTabClick(tabView, i);
            //获取菜单的内容
            View menuView = mBaseMenuAdapter.getMenuView(i, mMenuContainerView);
            menuView.setVisibility(GONE);
            mMenuContainerView.addView(menuView);
        }
        //刚开始的时候menu和shadow不显示
    }

    private void setTabClick(final View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPosition == -1) {
                    openMenu(position, tabView);
                } else {
                    mClosePostion=position;
                    closeMenu(position, tabView);
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
    private void openMenu(int position, View tabView) {
        mShadowView.setVisibility(VISIBLE);
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", -menuContentHeight, 0);
        translationAnimator.setDuration(350);
        translationAnimator.start();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 0f, 1f);
        alphaAnimator.setDuration(350);
        alphaAnimator.start();
        mCurrentPosition = position;

    }

    /**
     * 关闭菜单
     *
     * @param position
     * @param tabView
     */
    private void closeMenu(final int position, View tabView) {
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", 0, -menuContentHeight);
        translationAnimator.setDuration(350);
        translationAnimator.start();
        mShadowView.setVisibility(VISIBLE);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 1f, 0f);
        alphaAnimator.setDuration(350);
        alphaAnimator.start();
        //动画结束时，隐藏当前菜单
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                View menuView = mMenuContainerView.getChildAt(position);
                menuView.setVisibility(GONE);
            }
        });
        mCurrentPosition = -1;
    }

    //阴影部点击事件
    @Override
    public void onClick(View v) {
        closeMenu(mClosePostion,null);
    }
}
