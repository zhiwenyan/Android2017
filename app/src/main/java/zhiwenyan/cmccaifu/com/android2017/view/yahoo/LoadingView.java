package zhiwenyan.cmccaifu.com.android2017.view.yahoo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/8/28.
 */

public class LoadingView extends View {
    private float mCurrentRotationAngle = 0F;
    private int[] mCircleColors;
    private Paint mPaint;
    private float mRotationRadius;
    private boolean mInitParam;
    private float mCircleRadius;
    private int mCenterX, mCenterY;
    private int mSplashColor = Color.WHITE;
    private LoadingState mLoadingState;
    // 屏幕对角线的一半
    private float mDiagonalDist;
    // 空心圆初始半径
    private float mHoleRadius = 0F;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCircleColors = getResources().getIntArray(R.array.splash_circle_colors);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mInitParam) {
            initParam();
        }
        if (mLoadingState == null) {
            mLoadingState = new RotationState();
        }
        mLoadingState.draw(canvas);
    }

    private void initParam() {
        mRotationRadius = getMeasuredWidth() / 4;
        mCircleRadius = mRotationRadius / 8;
        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;
        mDiagonalDist = ( float ) Math.sqrt(mCenterX * mCenterX + mCenterY * mCenterY);
        mInitParam = true;


    }

    public void disappear() {
        //开始聚合动画
        if (mLoadingState instanceof RotationState) {
            RotationState r = new RotationState();
            r.cancel();
        }
        mLoadingState = new MergeState();
    }

    public abstract class LoadingState {
        public abstract void draw(Canvas canvas);
    }

    /**
     * 旋转动画
     */
    public class RotationState extends LoadingState {
        private ValueAnimator mAnimator;

        public RotationState() {
            if (mAnimator == null) {
                mAnimator = ObjectAnimator.ofFloat(0, 2 * ( float ) Math.PI);
                mAnimator.setDuration(2888);
                mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mCurrentRotationAngle = ( float ) animation.getAnimatedValue();
                        invalidate();

                    }
                });
                //不断反复执行
                mAnimator.setInterpolator(new LinearInterpolator());
                mAnimator.setRepeatCount(-1);
                mAnimator.start();
            }
        }

        @Override
        public void draw(Canvas canvas) {

            canvas.drawColor(mSplashColor);
            //画六个圆
            //每一份的角度
            double percentAngle = Math.PI * 2 / mCircleColors.length;
            for (int i = 0; i < mCircleColors.length; i++) {
                double currentAngle = percentAngle * i + mCurrentRotationAngle;
                int cx = ( int ) (mCenterX + mRotationRadius * Math.cos(currentAngle));
                int cy = ( int ) (mCenterY + mRotationRadius * Math.sin(currentAngle));
                mPaint.setColor(mCircleColors[i]);
                canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
            }
        }

        /**
         * 取消动画
         */
        public void cancel() {
            mAnimator.cancel();
        }
    }

    public class MergeState extends LoadingState {
        private ValueAnimator mAnimator;

        public MergeState() {
            if (mAnimator == null) {
                mAnimator = ObjectAnimator.ofFloat(mRotationRadius, 0);
                mAnimator.setDuration(600);
                // 开始的时候向后然后向前甩
                mAnimator.setInterpolator(new AnticipateInterpolator(3f));
                mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mRotationRadius = ( float ) animation.getAnimatedValue();// 最大半径到 0
                        // 重新绘制
                        invalidate();
                    }
                });
                mAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mLoadingState = new ExpandState();
                    }
                });
                mAnimator.start();
            }
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawColor(mSplashColor);
            //画六个圆
            //每一份的角度
            double percentAngle = Math.PI * 2 / mCircleColors.length;
            for (int i = 0; i < mCircleColors.length; i++) {
                double currentAngle = percentAngle * i + mCurrentRotationAngle;
                int cx = ( int ) (mCenterX + mRotationRadius * Math.cos(currentAngle));
                int cy = ( int ) (mCenterY + mRotationRadius * Math.sin(currentAngle));
                mPaint.setColor(mCircleColors[i]);
                canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
                requestLayout();
            }
        }
    }

    public class ExpandState extends LoadingState {
        private ValueAnimator mAnimator;

        public ExpandState() {
            if (mAnimator == null) {
                mAnimator = ObjectAnimator.ofFloat(0, mDiagonalDist);
                mAnimator.setDuration(1288);
                mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mHoleRadius = ( float ) animation.getAnimatedValue();
                        // 重新绘制
                        invalidate();
                    }
                });
                mAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //mLoadingState = new ExpandState();
                    }
                });
                mAnimator.start();
            }
        }

        @Override
        public void draw(Canvas canvas) {
            // 画笔的宽度
            float strokeWidth = mDiagonalDist - mHoleRadius;
            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setColor(mSplashColor);
            mPaint.setStyle(Paint.Style.STROKE);//空心圆
            Log.e("TAG", "mHoleRadius -> " + mHoleRadius);
            float radius = strokeWidth / 2 + mHoleRadius;
            // 绘制一个圆
            canvas.drawCircle(mCenterX, mCenterY, radius, mPaint);
        }
    }
}
