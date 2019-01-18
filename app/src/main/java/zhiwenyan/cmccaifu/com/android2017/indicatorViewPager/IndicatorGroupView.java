package zhiwenyan.cmccaifu.com.android2017.indicatorViewPager;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * ViewPager 指示器 Adapter适配
 * <p>
 * 如何做到适配所有 Adapter适配器模式
 * Created by yanzhiwen on 2017/8/31
 */

public class IndicatorGroupView extends FrameLayout {

    private LinearLayout mIndicatorGroup;
    private View mBottomTrackView;
    private int mItemWidth;
    private FrameLayout.LayoutParams mParams;
    private int mInitLeftMargin;

    public IndicatorGroupView(@NonNull Context context) {
        this(context, null);
    }

    public IndicatorGroupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorGroupView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIndicatorGroup = new LinearLayout(context);
        addView(mIndicatorGroup);
    }

    /**
     * @param itemView
     */
    public void addItemView(View itemView) {
        mIndicatorGroup.addView(itemView);
    }

    public View getItemView(int position) {
        return mIndicatorGroup.getChildAt(position);
    }

    public void addBottomTrackView(View trackView, int itemWidth) {
        if (trackView == null) {
            return;
        }
        this.mBottomTrackView = trackView;
        this.mItemWidth = itemWidth;
        addView(trackView);
        mParams = ( LayoutParams ) mBottomTrackView.getLayoutParams();
        mParams.gravity = Gravity.BOTTOM;

        int trackWidth = mParams.width;
        if (trackWidth == ViewGroup.LayoutParams.MATCH_PARENT) {
            trackWidth = mItemWidth;
        }
        if (trackWidth > mItemWidth) {
            trackWidth = mItemWidth;
        }
        mParams.width = trackWidth;
        mInitLeftMargin = (mItemWidth - trackWidth) / 2;
        mParams.leftMargin = mInitLeftMargin;
        mBottomTrackView.setLayoutParams(mParams);
    }

    /***
     * 移动底部指示器的位置
     *
     * @param position
     * @param positionOffset
     */
    public void scrollBottomTrackView(int position, float positionOffset) {
        int currentLeftMargin = mParams.leftMargin + mInitLeftMargin;
        int finalLeftMargin = ( int ) (position + positionOffset) * mItemWidth;
        int distance = finalLeftMargin - currentLeftMargin;
        ValueAnimator animator = ObjectAnimator.ofFloat(currentLeftMargin, Math.abs(finalLeftMargin))
                .setDuration(( long ) (Math.abs(distance) * 0.3f));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentLeftMargin = ( float ) animation.getAnimatedValue();
                Log.i("TAG", "currentLeftMargin: " + currentLeftMargin);
                mParams.leftMargin = ( int ) currentLeftMargin;
                mBottomTrackView.setLayoutParams(mParams);
            }
        });
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();

    }

    public void scrollToBottomView(int position) {
        int finalLeftMargin = position * mItemWidth + mInitLeftMargin;
        int currentLeftMargin = mParams.leftMargin;
        int distance = finalLeftMargin - currentLeftMargin;
        ValueAnimator animator = ObjectAnimator.ofFloat(currentLeftMargin,
                finalLeftMargin).setDuration(( long ) (Math.abs(distance) * 0.3f));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentLeftMargin = ( float ) animation.getAnimatedValue();
                mParams.leftMargin = ( int ) currentLeftMargin;
                mBottomTrackView.setLayoutParams(mParams);
            }
        });
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }
}
