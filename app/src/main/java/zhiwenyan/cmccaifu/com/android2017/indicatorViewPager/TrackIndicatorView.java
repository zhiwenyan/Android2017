package zhiwenyan.cmccaifu.com.android2017.indicatorViewPager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.view.textView.ColorTrackTextView;

/**
 * Created by yanzhiwen on 2017/8/31.
 */

public class TrackIndicatorView extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private IndicatorAdapter mAdapter;
    private IndicatorGroupView mIndicatorGroup;
    private int mTabVisibleNums = 0;
    private int mItemWidth;
    private ViewPager mViewPager;
    private int mCurrentPosition;
    private boolean mIsExecuteScroll;  //解决点击抖动的问题

    public TrackIndicatorView(Context context) {
        this(context, null);
    }

    public TrackIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrackIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIndicatorGroup = new IndicatorGroupView(context);
        addView(mIndicatorGroup);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TrackIndicatorView);
        mTabVisibleNums = array.getInteger(R.styleable.TrackIndicatorView_tabVisibleNums, mTabVisibleNums);
        array.recycle();
    }

    /**
     * 设置一个适配器
     *
     * @param adapter
     */
    public void setAdapter(IndicatorAdapter adapter) {
        if (adapter == null) {
            throw new NullPointerException("adapter not null");
        }
        this.mAdapter = adapter;
        int itemCount = mAdapter.getCount();
        for (int i = 0; i < itemCount; i++) {
            View itemView = mAdapter.getView(i, mIndicatorGroup);
            mIndicatorGroup.addItemView(itemView);
            if (mViewPager != null) {
                switchItemClick(itemView, i);
            }
        }
        //默认点亮第一个位置
        mAdapter.highIndicator(mIndicatorGroup.getItemView(0));
    }

    public void setAdapter(IndicatorAdapter adapter, ViewPager viewPager) {
        if (viewPager == null) {
            throw new NullPointerException("viewPager not null");
        }
        this.mViewPager = viewPager;
        setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mCurrentPosition = position;
        if (mIsExecuteScroll) {
            scrollCurrentIndicator(position, positionOffset);
            mIndicatorGroup.scrollBottomTrackView(position, positionOffset);
            //如果是点击 不执行onPageScrolled方法

            if (positionOffset > 0) {
                // 获取左边
                ColorTrackTextView left = (ColorTrackTextView) mIndicatorGroup.getItemView(position);
                // 设置朝向
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                // 设置进度  positionOffset 是从 0 一直变化到 1
                left.setCurrentProgress(1 - positionOffset);

                // 获取右边
                ColorTrackTextView right = (ColorTrackTextView) mIndicatorGroup.getItemView(position + 1);
                right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                right.setCurrentProgress(positionOffset);
            }
        }
//        // 默认一进入就选中第一个
//        ColorTrackTextView left = (ColorTrackTextView) mIndicatorGroup.getItemView(0);
//        left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
//        left.setCurrentProgress(1);

    }


    @Override
    public void onPageSelected(int position) {
        mAdapter.restoreIndicator(mIndicatorGroup.getItemView(mCurrentPosition));
        mCurrentPosition = position;
        mAdapter.highIndicator(mIndicatorGroup.getItemView(mCurrentPosition));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1) {
            mIsExecuteScroll = true;
        }
        if (state == 0) {
            mIsExecuteScroll = false;
        }
    }

    private void scrollCurrentIndicator(int position, float positionOffset) {
        //positionOffset 左滑动[0-1)，右滑动(1-0].
        //当前总共的位置
        float totalScroll = (position + positionOffset) * mItemWidth;
        //左边的偏移
        int offsetScroll = (getWidth() - mItemWidth) / 2;
        //最终的一个偏移量
        final int finalScroll = (int) (totalScroll - offsetScroll);
        Log.i("TAG", "scrollCurrentIndicator: " + finalScroll);
        scrollTo(finalScroll, 0);
    }

    int parentWidth;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            parentWidth = getMeasuredWidth();
            mItemWidth = getItemWidth();
            for (int i = 0; i < mAdapter.getCount(); i++) {
                ColorTrackTextView colorTrackTextView = (ColorTrackTextView) mIndicatorGroup.getItemView(i);
                ViewGroup.LayoutParams params = colorTrackTextView.getLayoutParams();
                params.width = mItemWidth;
                colorTrackTextView.setLayoutParams(params);
            }
            //添加指示器
            mIndicatorGroup.addBottomTrackView(mAdapter.getBottomTrackView(), mItemWidth);
        }
    }

    /**
     * @param itemView
     * @param position
     */
    private void switchItemClick(View itemView, final int position) {
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(position);
                smoothIndicator(position);
                //移动下标
                mIndicatorGroup.scrollToBottomView(position);
            }

        });
    }

    /**
     * 点击移动  而且带动画
     *
     * @param position
     */
    private void smoothIndicator(int position) {
        //positionOffset 左滑动[0-1)，右滑动(1-0].
        //当前总共的位置
        float totalScroll = (position) * mItemWidth;
        //左边的偏移
        int offsetScroll = (getWidth() - mItemWidth) / 2;
        //最终的一个偏移量
        final int finalScroll = (int) (totalScroll - offsetScroll);
        Log.i("TAG", "scrollCurrentIndicator: " + finalScroll);
        smoothScrollTo(finalScroll, 0);
    }

    public int getItemWidth() {
        int parentWidth = getMeasuredWidth();
        if (mTabVisibleNums != 0) {
            return parentWidth / mTabVisibleNums;
        }
        //没有指定  --->拿最宽的作为每个itemView的宽度
        int itemWidth = 0;
        int maxItemWidth = 0;
        int allWidth = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            //指定Item的宽度
            int currentWidth = mIndicatorGroup.getChildAt(i).getWidth();
            maxItemWidth = Math.max(currentWidth, maxItemWidth);
            allWidth += currentWidth;
        }
        itemWidth = maxItemWidth;
        if (allWidth < parentWidth) {
            itemWidth = parentWidth / mAdapter.getCount();
        }
        return itemWidth;
    }
}
