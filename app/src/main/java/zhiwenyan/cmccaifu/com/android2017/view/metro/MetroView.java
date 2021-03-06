package zhiwenyan.cmccaifu.com.android2017.view.metro;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Description:
 * Data：10/31/2018-4:21 PM
 *
 * @author yanzhiwen
 */
public class MetroView extends RelativeLayout {
    private View mLeftView;
    private View mMiddleView;
    private View mRightView;
    private boolean mIsStopAnimator;
    private float mScaleFactor = 1.3f;
    private LinearLayout mLinearLayout;

    public MetroView(Context context) {
        this(context, null);
    }

    public MetroView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetroView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLeftView = getCircleView(context, Color.RED);
        mMiddleView = getCircleView(context, Color.GREEN);
        mRightView = getCircleView(context, Color.BLUE);

        mLinearLayout = new LinearLayout(context);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLinearLayout.addView(mLeftView);
        mLinearLayout.addView(mMiddleView);
        mLinearLayout.addView(mRightView);

        LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //中间的位置
        layoutParams.addRule(CENTER_IN_PARENT);
        mLinearLayout.setLayoutParams(layoutParams);
        addView(mLinearLayout);

        LinearLayout.LayoutParams middleViewParams = (LinearLayout.LayoutParams) mMiddleView.getLayoutParams();
        middleViewParams.leftMargin = dip2px(8);
        middleViewParams.rightMargin = dip2px(8);
        mMiddleView.setLayoutParams(middleViewParams);

        LinearLayout.LayoutParams leftViewParams = (LinearLayout.LayoutParams) mMiddleView.getLayoutParams();
        leftViewParams.leftMargin = dip2px(8);
        mLeftView.setLayoutParams(leftViewParams);


        LinearLayout.LayoutParams rightViewParams = (LinearLayout.LayoutParams) mMiddleView.getLayoutParams();
        rightViewParams.rightMargin = dip2px(8);
        mRightView.setLayoutParams(rightViewParams);


        post(new Runnable() {
            @Override
            public void run() {
                startEnlargeAnimation(mLeftView);
            }
        });
    }

    private CircleView getCircleView(Context context, int color) {
        CircleView circleView = new CircleView(context);
        circleView.setColor(color);
        LayoutParams layoutParams = new LayoutParams(dip2px(16), dip2px(16));
        circleView.setLayoutParams(layoutParams);
        return circleView;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * 放大动画
     *
     * @param view
     */
    private void startEnlargeAnimation(View view) {
        if (mIsStopAnimator) {
            return;
        }
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.3f);
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.3f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleAnimatorX, scaleAnimatorY);
        animatorSet.setDuration(288);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startScaleAnimation(view);
            }
        });
        animatorSet.start();

    }

    /**
     * 缩小动画
     *
     * @param view
     */
    private void startScaleAnimation(View view) {
        if (mIsStopAnimator) {
            return;
        }
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 1.3f, 1.0f);
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 1.3f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleAnimatorX, scaleAnimatorY);
        animatorSet.setDuration(288);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                exchangeScaleView(view);
            }
        });
        animatorSet.start();
    }


    private void exchangeScaleView(View view) {
        if (view == mLeftView) {
            startEnlargeAnimation(mMiddleView);
        } else if (view == mMiddleView) {
            startEnlargeAnimation(mRightView);
        } else {
            startEnlargeAnimation(mLeftView);
        }
    }

    public void dismiss() {
        mLeftView.clearAnimation();
        mMiddleView.clearAnimation();
        mRightView.clearAnimation();
        mIsStopAnimator = true;
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }
}
