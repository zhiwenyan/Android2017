package zhiwenyan.cmccaifu.com.android2017.view.live;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:点赞的效果
 * Data：1/4/2018-5:51 PM
 *
 * @author: yanzhiwen
 */
public class LoveLayout extends RelativeLayout {
    private Random mRandom = new Random();
    private int[] mImageRes = new int[]{R.mipmap.pl_red, R.mipmap.pl_blue, R.mipmap.pl_yellow};
    private int mWidth;
    private int mHeight;
    private int mDrawableWidth;
    private int mDrawableHeight;
    private Interpolator[] mInterpolators;

    public LoveLayout(Context context) {
        this(context, null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        addLove();
        mInterpolators = new Interpolator[]{new AccelerateDecelerateInterpolator(),
                new LinearInterpolator(),
                new DecelerateInterpolator()};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    public void addLove() {
        final ImageView loveLv = new ImageView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        loveLv.setLayoutParams(params);
        loveLv.setImageResource(mImageRes[mRandom.nextInt(mImageRes.length - 1)]);
        addView(loveLv);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.mipmap.pl_red);
        mDrawableWidth = drawable.getIntrinsicWidth();
        mDrawableHeight = drawable.getIntrinsicHeight();
        AnimatorSet animatorSet = getAnimator(loveLv);
        animatorSet.setDuration(1888);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(loveLv);
            }
        });
        animatorSet.start();
    }

    private AnimatorSet getAnimator(ImageView iv) {
        AnimatorSet animator = new AnimatorSet();
        //添加效果，有放大的效果和透明度的变化（属性动画）
        AnimatorSet innerAnimatorSet = new AnimatorSet();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(iv, "alpha", 0.3f, 1.0f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 0.3f, 1.0f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(iv, "scaleY", 0.3f, 1.0f);
        innerAnimatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        innerAnimatorSet.setDuration(350);
        //innerAnimatorSet.start();
        //运行的路径动画  playSequentially 按照顺序执行
        animator.playSequentially(innerAnimatorSet, getBezierAnimator(iv));
        return animator;
    }

    public Animator getBezierAnimator(final ImageView iv) {
        //怎样去确定这4个点
        PointF point0 = new PointF(mWidth / 2 - mDrawableWidth / 2, mHeight - mDrawableHeight / 2);
        //确定p1的y值要大于p2的y值
        PointF point1 = getPoint(2);
        PointF point2 = getPoint(1);
        PointF point3 = new PointF(mRandom.nextInt(mWidth - mDrawableWidth), 0);

        LoveTypeEvaluator evaluator = new LoveTypeEvaluator(point1, point2);
        ValueAnimator bezierAnimator = ObjectAnimator.ofObject(evaluator, point0, point3);
        bezierAnimator.setInterpolator(mInterpolators[mRandom.nextInt(mInterpolators.length - 1)]);
        bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                iv.setX(pointF.x);
                iv.setY(pointF.y);
                float t = animation.getAnimatedFraction();
                iv.setAlpha(1 - t + 0.2f);
            }
        });
        return bezierAnimator;
    }

    private PointF getPoint(int index) {
        return new PointF(mRandom.nextInt(mWidth - mDrawableWidth), mRandom.nextInt(mHeight / 2)
                + (index - 1) * mHeight / 2);
    }
}
