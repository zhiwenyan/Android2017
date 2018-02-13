package zhiwenyan.cmccaifu.com.android2017.view.ball;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：2/12/2018-5:11 PM
 *
 * @author: yanzhiwen
 */
public class BouncyBall extends LinearLayout {
    private CircleView mShapeView;
    private View mShadowView;
    private int mTransY = dip2px(100);
    private int mUpHeight;

    public BouncyBall(Context context) {
        this(context, null);
    }

    public BouncyBall(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BouncyBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        //传this把这个布局加载中LinearLayout中
        inflate(getContext(), R.layout.wuba_loading, this);
        mShapeView = findViewById(R.id.shapeView);
        mShadowView = findViewById(R.id.shadowView);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                startFallAnimator();
            }
        }, 888);

    }

    private void startFallAnimator() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mShapeView, "translationY", mUpHeight, mTransY);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mShadowView, "scaleX", 1.0f, 0.3f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(translationY, alpha);
        animatorSet.setDuration(888);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startUpAnimator();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        animatorSet.start();
    }

    private void startUpAnimator() {
        if (dip2px(mUpHeight) > mTransY) {
            return;
        }
        mUpHeight = dip2px(mUpHeight + 5);
        Log.i("TAG", "startUpAnimator: " + mUpHeight);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mShapeView, "translationY", mTransY, dip2px(mUpHeight));
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mShadowView, "scaleX", 0.3f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(translationY, alpha);
        animatorSet.setDuration(888);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startFallAnimator();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        animatorSet.start();
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }
}
