package zhiwenyan.cmccaifu.com.android2017.view.Loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by zhiwenyan on 2017/8/18.
 */

public class LoadingView extends RelativeLayout {
    private CircleView mLeftView, mMiddleView, mRightView;
    private boolean mIsStopAnimator;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLeftView = getCircleView(context);
        mLeftView.exchangeColor(Color.BLUE);
        mMiddleView = getCircleView(context);
        mMiddleView.exchangeColor(Color.RED);
        mRightView = getCircleView(context);
        mRightView.exchangeColor(Color.GREEN);
        addView(mLeftView);
        addView(mRightView);
        addView(mMiddleView);
        post(new Runnable() {
            @Override
            public void run() {
                expandAnimation();
            }
        });
    }


    private CircleView getCircleView(Context context) {
        CircleView circleView = new CircleView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dip2px(10), dip2px(10));
        params.addRule(CENTER_IN_PARENT);
        circleView.setLayoutParams(params);
        return circleView;
    }


    private void expandAnimation() {
        if (mIsStopAnimator) {
            return;
        }
        ObjectAnimator leftTranslationAnimator = ObjectAnimator.ofFloat(mLeftView, "translationX", 0, -dip2px(28));
        ObjectAnimator rightTranslationAnimator = ObjectAnimator.ofFloat(mRightView, "translationX", 0, dip2px(28));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(leftTranslationAnimator, rightTranslationAnimator);
        animatorSet.setDuration(388);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                innerAnimation();
            }
        });
        animatorSet.start();


    }


    private void innerAnimation() {
        ObjectAnimator leftTranslationAnimator = ObjectAnimator.ofFloat(mLeftView, "translationX", -dip2px(28), 0);
        ObjectAnimator rightTranslationAnimator = ObjectAnimator.ofFloat(mRightView, "translationX", dip2px(28), 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(leftTranslationAnimator, rightTranslationAnimator);
        animatorSet.setDuration(388);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //往里面跑
                expandAnimation();
                //切换颜色
                //左边的给中间
                int leftColor = mLeftView.getColor();
                int middleColor = mMiddleView.getColor();
                int rightColor = mRightView.getColor();
                mMiddleView.exchangeColor(leftColor);
                mRightView.exchangeColor(middleColor);
                mLeftView.exchangeColor(rightColor);
            }
        });
        animatorSet.start();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(INVISIBLE);
        mLeftView.clearAnimation();
        mRightView.clearAnimation();
        mMiddleView.clearAnimation();
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
            removeAllViews();
        }
        mIsStopAnimator = true;
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }
}
