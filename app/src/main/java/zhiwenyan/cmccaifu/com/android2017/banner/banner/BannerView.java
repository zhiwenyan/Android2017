package zhiwenyan.cmccaifu.com.android2017.banner.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
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
    //底部的指示器的View
    private LinearLayout mDotContainerView;
    private BannerAdapter mAdapter;
    private Context mContext;
    private Drawable mIndicatorFocusDrawable;
    private Drawable mIndicatorNormalDrawable;
    private int mCurrentPosition;
    private int mDotGravity = -1;
    private int mDotSize = 8;
    private int mDotDistance = 2;
    //底部颜色默认透明
    private int mBottomColor = Color.TRANSPARENT;
    private View mBannerBottomView;
    private float mWidthProportion;
    private float mHeightProportion;

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
        initAttribute(attrs);
        initView();

    }

    /**
     * 初始化自定义属性
     *
     * @param attrs
     */
    private void initAttribute(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.BannerView);
        mDotGravity = typedArray.getInt(R.styleable.BannerView_dotGravity, -1);
        mIndicatorFocusDrawable = typedArray.getDrawable(R.styleable.BannerView_dotIndicatorFocus);
        if (mIndicatorFocusDrawable == null) {
            mIndicatorFocusDrawable = new ColorDrawable(Color.RED);
        }
        mIndicatorNormalDrawable = typedArray.getDrawable(R.styleable.BannerView_dotIndicatorNormal);
        if (mIndicatorNormalDrawable == null) {
            mIndicatorNormalDrawable = new ColorDrawable(Color.WHITE);
        }
        mDotSize = (int) typedArray.getDimension(R.styleable.BannerView_dotSize, DensityUtil.dip2px(mContext, 8));
        mDotDistance = (int) typedArray.getDimension(R.styleable.BannerView_dotDistance, DensityUtil.dip2px(mContext, 2));
        mBottomColor = typedArray.getColor(R.styleable.BannerView_bottomColor, mBottomColor);

        mWidthProportion = typedArray.getFloat(R.styleable.BannerView_widthProportion, mWidthProportion);
        mHeightProportion = typedArray.getFloat(R.styleable.BannerView_heightProportion, mHeightProportion);
        typedArray.recycle();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mBannerViewPager = findViewById(R.id.bannerViewPager);
        mDotContainerView = findViewById(R.id.dot_container);
        mBannerBottomView = findViewById(R.id.bannerBottomView);
        mBannerBottomView.setBackgroundColor(mBottomColor);
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * 设置适配器adapter
     *
     * @param adapter
     */
    public void setAdapter(BannerAdapter adapter) {
        this.mAdapter = adapter;
        mBannerViewPager.setAdapter(adapter);
        initDotIndicator();
        mBannerViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //监听当前选中的位置
                pageSelect(position);
            }
        });
        post(new Runnable() {
            @Override
            public void run() {
                if (mHeightProportion == 0 || mWidthProportion == 0) {
                    return;
                }
                int width = getMeasuredWidth();
                //计算高度
                int height = (int) (width * mHeightProportion / mWidthProportion);
                //指定宽高
                getLayoutParams().height = height;
                mBannerViewPager.getLayoutParams().height = height;
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

    private void initDotIndicator() {
        //获取广告位的数量
        int count = mAdapter.getCount();
        //设置点的位置
        mDotContainerView.setGravity(getDotGravity());
        for (int i = 0; i < count; i++) {
            DotIndicatorView dot = new DotIndicatorView(mContext);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(mDotSize, mDotSize);
            param.leftMargin = param.rightMargin = mDotDistance;
            dot.setLayoutParams(param);
            if (i == 0) {
                dot.setDrawable(mIndicatorFocusDrawable);
            } else {
                dot.setDrawable(mIndicatorNormalDrawable);
            }
            mDotContainerView.addView(dot);
        }
    }

    public int getDotGravity() {
        switch (mDotGravity) {
            case 0:
                return Gravity.CENTER;
            case 1:
                return Gravity.RIGHT;
            case -1:
                return Gravity.LEFT;
        }
        return Gravity.RIGHT;
    }
}
