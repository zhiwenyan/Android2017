package zhiwenyan.cmccaifu.com.android2017.banner.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;

/**
 * Created by zhiwenyan on 6/1/17.
 */

public class BannerView extends RelativeLayout {
    private BannerViewPager mBannerViewPager;
    private LinearLayout mDotContainerView;
    private BannerAdapter mAdapter;
    private Context mContext;
    private Drawable mIndicatorFocusDrawable;
    private Drawable mIndicatorNormalDrawable;
    private int mCurrentPosition;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.banner_layout, this);
        this.mContext = context;
        initView();

    }

    /**
     * 初始化View
     */
    private void initView() {
        mBannerViewPager = (BannerViewPager) findViewById(R.id.bannerViewPager);
        mDotContainerView = (LinearLayout) findViewById(R.id.dot_container);
        mIndicatorFocusDrawable = new ColorDrawable(Color.RED);
        mIndicatorNormalDrawable = new ColorDrawable(Color.WHITE);
        mBannerViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                if (position > 0 && position <= 1) {
                    page.setPivotX(0);
                    page.setScaleX(1 - position);
                } else if (position >= -1 && position < 0) {
                    page.setPivotX(page.getWidth());
                    page.setScaleX(1 + position);
                }
            }
        });
    }

    public void setAdapter(BannerAdapter adapter) {
        this.mAdapter = adapter;
        mBannerViewPager.setAdapter(adapter);
        initDotIndicatior();
        mBannerViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //监听当前选中的位置
                pageSelect(position);
            }
        });


    }

    private void pageSelect(int position) {
        DotIndicatorView dotIndicatorView = (DotIndicatorView) mDotContainerView.
                getChildAt(mCurrentPosition);
        dotIndicatorView.setDrawable(mIndicatorNormalDrawable);
        mCurrentPosition = position % mAdapter.getCount();

        DotIndicatorView mCurrentIndicatorView = (DotIndicatorView) mDotContainerView.
                getChildAt(mCurrentPosition);
        mCurrentIndicatorView.setDrawable(mIndicatorFocusDrawable);
    }

    public void startLoop() {
        mBannerViewPager.startLoop();
    }

    public void setScrollerDuration(int scrollerDuration) {
        mBannerViewPager.setScrollerDuration(scrollerDuration);
    }

    private void initDotIndicatior() {
        //获取广告位的数量
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            DotIndicatorView dot = new DotIndicatorView(mContext);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 8),
                    DensityUtil.dip2px(mContext, 8));
            param.leftMargin = param.rightMargin = DensityUtil.dp2px(2);
            dot.setLayoutParams(param);
            if (i == 0) {
                dot.setDrawable(mIndicatorFocusDrawable);
            } else {
                dot.setDrawable(mIndicatorNormalDrawable);
            }
            mDotContainerView.addView(dot);
        }
    }

}
