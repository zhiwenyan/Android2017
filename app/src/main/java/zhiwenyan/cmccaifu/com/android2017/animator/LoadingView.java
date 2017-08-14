package zhiwenyan.cmccaifu.com.android2017.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 2017/8/10.
 */

public class LoadingView extends LinearLayout {
    private ShapeView mShapeView;
    private View mShadowView;
    private int mTransY;
    private final long ANIMATOR_DURATION = 350l;
    private boolean mIsStopAnimator;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTransY = dip2px(80);
        initLayout();
    }

    private void initLayout() {
        //传this把这个布局加载中LinearLayout中
        inflate(getContext(), R.layout.wuba_loading, this);
        mShapeView = (ShapeView) findViewById(R.id.shapeView);
        mShadowView = findViewById(R.id.shadowView);
        post(new Runnable() {
            @Override
            public void run() {
                startFallAnimator();
            }
        });
    }

    /**
     * 开始下落动画
     */
    private void startFallAnimator() {
        if (mIsStopAnimator)
            return;

        ObjectAnimator translationY = ObjectAnimator.ofFloat(mShapeView, "translationY", 0, mTransY);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 1.0f, 0.3f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateInterpolator());  //插值器
        animatorSet.playTogether(translationY, scaleAnimator);
        animatorSet.setDuration(ANIMATOR_DURATION);
        //下落完之后可以上抛了
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startUpAnimator();
                //改变形状
                mShapeView.exchange();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                startRotationAnimator();
            }
        });
        animatorSet.start();


    }

    /**
     * 旋转动画
     */
    private void startRotationAnimator() {
        ObjectAnimator rotation = null;
        switch (mShapeView.getCurrentShape()) {
            case Circle:
                break;
            case Square:
                rotation = ObjectAnimator.ofFloat(mShapeView, "rotation", 0, 180f);
                break;
            case Triangle:
                rotation = ObjectAnimator.ofFloat(mShapeView, "rotation", 0, -120f);
                break;
        }
        if (rotation != null) {
            rotation.setDuration(ANIMATOR_DURATION);
            rotation.start();
        }
    }

    /**
     * 开始上抛动画
     */
    private void startUpAnimator() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mShapeView, "translationY", mTransY, 0);
        translationY.setDuration(ANIMATOR_DURATION);

        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(mShadowView, "scaleX", 0.3f, 1f);
        scaleAnimator.setDuration(ANIMATOR_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(translationY, scaleAnimator);
        animatorSet.start();
        //下落完之后可以上抛了
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startFallAnimator();


            }
        });
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }

    //只是代码设置了隐藏，动画还在内存中跑
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(View.INVISIBLE);  //不要再去排放和计算，少走些系统的源码
        //清除动画
        mShapeView.clearAnimation();
        mShadowView.clearAnimation();
        //移除LoadingView
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
            removeAllViews();
        }
        mIsStopAnimator = true;

    }
}
