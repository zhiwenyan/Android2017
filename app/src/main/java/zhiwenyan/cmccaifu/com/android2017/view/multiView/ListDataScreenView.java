package zhiwenyan.cmccaifu.com.android2017.view.multiView;

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

public class ListDataScreenView extends LinearLayout {
    private LinearLayout mTabMenuView;
    private Context mContext;
    private FrameLayout mMiddleView;
    private View mShadowView;
    private int mShadowViewColor = Color.parseColor("#cecece");
    private int menuContentHeight;
    private FrameLayout mContainerView;
    private BaseMenuAdapter mBaseMenuAdapter;
    private int mCurrentPosition = -1;

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
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTabMenuView.setLayoutParams(params);
        addView(mTabMenuView);

        mMiddleView = new FrameLayout(mContext);
        LinearLayout.LayoutParams middleParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        middleParam.weight = 1;
        mMiddleView.setLayoutParams(middleParam);
        addView(mMiddleView);


        //创建菜单，用来存放菜单内容
        mContainerView = new FrameLayout(mContext);
        mContainerView.setBackgroundColor(Color.WHITE);
        addView(mContainerView);

        mShadowView = new View(mContext);
        mShadowView.setBackgroundColor(mShadowViewColor);
      //  mShadowView.setAlpha(0f);
      //  mShadowView.setVisibility(GONE);
        addView(mShadowView);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //内容的高度
        int height = MeasureSpec.getSize(heightMeasureSpec);
        menuContentHeight = (int) (height * 75f / 100);
        ViewGroup.LayoutParams params = mContainerView.getLayoutParams();
        params.height = menuContentHeight;
        mContainerView.setLayoutParams(params);
        // mContainerView.setTranslationY(-menuContentHeight);
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
            //菜单的tab
            View tabView = mBaseMenuAdapter.getTabView(i, mTabMenuView);
            mTabMenuView.addView(tabView);
            LinearLayout.LayoutParams params = (LayoutParams) tabView.getLayoutParams();
            params.weight = 1;
            tabView.setLayoutParams(params);
            setTabClick(tabView, i);
            //菜单的内容
            View menuView = mBaseMenuAdapter.getMenuView(i, mContainerView);
            menuView.setVisibility(GONE);
            mContainerView.addView(menuView);

        }
        //刚开始的时候menu和shadow不显示
    }

    private void setTabClick(final View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("------------onClick");
                if (mCurrentPosition == -1) {
                    openMenu(position, tabView);
                } else {
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
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mContainerView, "translationY", -menuContentHeight, 0);
        translationAnimator.setDuration(888);
        translationAnimator.start();
        mShadowView.setVisibility(VISIBLE);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 0f, 1f);
        alphaAnimator.setDuration(888);
        alphaAnimator.start();
        mCurrentPosition = position;

    }

    /**
     * 关闭菜单
     *
     * @param position
     * @param tabView
     */
    private void closeMenu(int position, View tabView) {
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mContainerView, "translationY", 0, -menuContentHeight);
        translationAnimator.setDuration(888);
        translationAnimator.start();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 1f, 0f);
        alphaAnimator.setDuration(888);
        alphaAnimator.start();
        mShadowView.setVisibility(VISIBLE);
        mCurrentPosition = -1;
    }

}
