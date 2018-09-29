package zhiwenyan.cmccaifu.com.android2017.view.GestureHandler;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.animation.AccelerateInterpolator;

/**
 * Description:
 * Data：9/18/2018-2:16 PM
 *
 * @author yanzhiwen
 */
public class MyImageView extends AppCompatImageView {
    private static final String TAG = MyImageView.class.getSimpleName();
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private int mBoundWidth = 0;
    private int mBoundHeight = 0;
    private boolean isAutoScale = false;
    private float scale = 1.0f;
    private float translateLeft = 0.0f;
    private float translateTop = 0.0f;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(getContext(), new GestureListener());
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureListener());
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        //手指滑动调用
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(TAG, "onScroll: ");
            final float mScaledWidth = mBoundWidth * scale;
            final float mScaledHeight = mBoundHeight * scale;
            Log.i(TAG, "onScroll:distanceX "+distanceX);
            Log.i(TAG, "onScroll:distanceY "+distanceY);
            if (mScaledHeight > getHeight()) {
                translateTop -= distanceY * 1.5;
                Log.i(TAG, "onScroll:translateTop "+translateTop);
                translateTop = getExplicitTranslateTop(translateTop);
                /*if (getDiffY() != 0) {
                    final float disY = (float) (Math.acos(Math.abs(getDiffY()) / getPanelHeight() * 6) * distanceY);
                    if (disY == disY) translateTop -= disY; // float 低值溢出变Nan数值
                } else {
                    translateTop -= distanceY * 1.5;
                }*/
            }

            boolean isReachBorder = false;
            if (mScaledWidth > getWidth()) {
                translateLeft -= distanceX * 1.5;
                Log.i(TAG, "onScroll:translateLeft "+translateLeft);

                final float t = getExplicitTranslateLeft(translateLeft);
                if (t != translateLeft) isReachBorder = true;
                translateLeft = t;
                /*if (getDiffX() != 0) {
                    final float disX = (float) (Math.acos(Math.abs(getDiffX()) / getWidth() * 4) * distanceX);
                    if (disX == disX) translateLeft -= disX;
                } else {
                    translateLeft -= distanceX * 1.5;
                }*/
            } else {
                isReachBorder = true;
            }
            invalidate();
            return true;
        }

        //手指双击调用
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "onDoubleTap: ");
            isAutoScale = true;
            ValueAnimator scaleAnimator = getResetScaleAnimator();
            if (scale == 1.0f) {
                scaleAnimator.setFloatValues(1.0f, 2.0f);
                ValueAnimator resetXAnimator = getResetXAnimator();
                ValueAnimator resetYAnimator = getResetYAnimator();

                resetXAnimator.setFloatValues(translateLeft, (getWidth() - mBoundWidth * 2.f) / 2.f);
                Log.i(TAG, "onDoubleTap: translateLeft " + (getWidth() - mBoundWidth * 2.f) / 2.f);
                resetYAnimator.setFloatValues(translateTop, getDefaultTranslateTop(getHeight(), mBoundHeight * 2));
                Log.i(TAG, "onDoubleTap: translateTop" + getDefaultTranslateTop(getHeight(), mBoundHeight * 2));

                resetXAnimator.addUpdateListener(getOnTranslateXAnimationUpdate());
                resetYAnimator.addUpdateListener(getOnTranslateYAnimationUpdate());
                resetXAnimator.start();
                resetYAnimator.start();
            } else {
                scaleAnimator.setFloatValues(scale, 1.0f);
                resetDefaultState();

            }
            scaleAnimator.addUpdateListener(getOnScaleAnimationUpdate());
            scaleAnimator.start();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling: ");

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    private class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            Log.i(TAG, "onScale: ");
            return true;
        }
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        super.setFrame(l, t, r, b);
        Drawable drawable = getDrawable();
        if (drawable == null) return false;
        if (mBoundWidth != 0 && mBoundHeight != 0 && scale != 1) return false;
        adjustBounds(getWidth(), getHeight());

        return true;
    }


    private void adjustBounds(int width, int height) {
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        mBoundWidth = drawable.getBounds().width();
        mBoundHeight = drawable.getBounds().height();
        float scale = ( float ) mBoundWidth / width;
        mBoundHeight /= scale;
        mBoundWidth = width;
        drawable.setBounds(0, 0, mBoundWidth, mBoundHeight);
        translateLeft = 0;
        translateTop = getDefaultTranslateTop(height, mBoundHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        adjustBounds(w, h);
    }


    private float getDefaultTranslateTop(int height, int boundHeight) {
        float top = (height - boundHeight) / 2.f;
        return top > 0 ? top : 0;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        //图片的宽和高
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();
        if (drawableWidth == 0 || drawableHeight == 0) {
            return;
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        canvas.translate(translateLeft, translateTop);
        canvas.scale(scale, scale);
        Log.i(TAG, "onDraw: translateLeft"+translateLeft);
        Log.i(TAG, "onDraw: translateTop"+translateTop);
        Log.i(TAG, "onDraw: scale"+scale);
        // 如果先scale,再translate,那么,真实translate的值是要与scale值相乘的
        drawable.draw(canvas);
        canvas.restoreToCount(saveCount);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            cancelAnimator();
        }
        mGestureDetector.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }




    private ValueAnimator resetScaleAnimator;
    private AccelerateInterpolator mAccelerateInterpolator = new AccelerateInterpolator();
    private FloatEvaluator mFloatEvaluator = new FloatEvaluator();


    /**
     * 重置伸缩
     *
     * @return resetScaleAnimator
     */
    private ValueAnimator getResetScaleAnimator() {
        if (resetScaleAnimator != null) {
            resetScaleAnimator.removeAllUpdateListeners();
        } else {
            resetScaleAnimator = ValueAnimator.ofFloat();
        }
        resetScaleAnimator.setDuration(150);
        resetScaleAnimator.setInterpolator(mAccelerateInterpolator);
        resetScaleAnimator.setEvaluator(mFloatEvaluator);
        return resetScaleAnimator;
    }

    private ValueAnimator resetXAnimator;
    private ValueAnimator resetYAnimator;

    /**
     * 水平方向的动画
     *
     * @return resetXAnimator
     */
    private ValueAnimator getResetXAnimator() {
        if (resetXAnimator != null) {
            resetXAnimator.removeAllUpdateListeners();
        } else {
            resetXAnimator = ValueAnimator.ofFloat();
        }
        resetXAnimator.setDuration(150);
        resetXAnimator.setInterpolator(mAccelerateInterpolator);
        resetXAnimator.setEvaluator(mFloatEvaluator);
        return resetXAnimator;
    }

    /**
     * 垂直方向的动画
     *
     * @return resetYAnimator
     */
    private ValueAnimator getResetYAnimator() {
        if (resetYAnimator != null) {
            resetYAnimator.removeAllUpdateListeners();
        } else {
            resetYAnimator = ValueAnimator.ofFloat();
        }
        resetYAnimator.setDuration(150);
        resetYAnimator.setInterpolator(mAccelerateInterpolator);
        resetYAnimator.setEvaluator(mFloatEvaluator);
        return resetYAnimator;
    }

    /**
     * 重置到初始的状态
     */
    private void resetDefaultState() {
        if (translateLeft != 0) {
            ValueAnimator mTranslateXAnimator = getResetXAnimator();
            mTranslateXAnimator.setFloatValues(translateLeft, 0);
            mTranslateXAnimator.addUpdateListener(getOnTranslateXAnimationUpdate());
            mTranslateXAnimator.start();
        }

        ValueAnimator mTranslateYAnimator = getResetYAnimator();
        mTranslateYAnimator.setFloatValues(translateTop, getDefaultTranslateTop(getHeight(), mBoundHeight));
        mTranslateYAnimator.addUpdateListener(getOnTranslateYAnimationUpdate());
        mTranslateYAnimator.start();

    }

    private ValueAnimator.AnimatorUpdateListener onScaleAnimationUpdate;

    /**
     * 重置伸缩动画的监听器
     *
     * @return
     */
    public ValueAnimator.AnimatorUpdateListener getOnScaleAnimationUpdate() {
        if (onScaleAnimationUpdate != null) return onScaleAnimationUpdate;
        onScaleAnimationUpdate = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = ( float ) animation.getAnimatedValue();
                invalidate();
            }
        };
        return onScaleAnimationUpdate;
    }

    /**
     * 水平动画的监听器
     *
     * @return
     */
    private ValueAnimator.AnimatorUpdateListener onTranslateXAnimationUpdate;

    public ValueAnimator.AnimatorUpdateListener getOnTranslateXAnimationUpdate() {
        if (onTranslateXAnimationUpdate != null) return onTranslateXAnimationUpdate;
        onTranslateXAnimationUpdate = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                translateLeft = ( float ) animation.getAnimatedValue();
                invalidate();
            }
        };
        return onTranslateXAnimationUpdate;
    }

    /**
     * 垂直动画的监听器
     *
     * @return
     */
    private ValueAnimator.AnimatorUpdateListener onTranslateYAnimationUpdate;

    public ValueAnimator.AnimatorUpdateListener getOnTranslateYAnimationUpdate() {
        if (onTranslateYAnimationUpdate != null) return onTranslateYAnimationUpdate;
        onTranslateYAnimationUpdate = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                translateTop = ( float ) animation.getAnimatedValue();
                invalidate();
            }
        };
        return onTranslateYAnimationUpdate;
    }
    private float getExplicitTranslateLeft(float l) {
        final float mScaledWidth = mBoundWidth * scale;
        if (l > 0) {
            l = 0;
        }
        if (-l + getWidth() > mScaledWidth) {
            l = getWidth() - mScaledWidth;
        }
        return l;
    }

    private float getExplicitTranslateTop(float t) {
        final float mScaledHeight = mBoundHeight * scale;
        if (t > 0) {
            t = 0;
        }
        if (-t + getHeight() > mScaledHeight) {
            t = getHeight() - mScaledHeight;
        }
        return t;
    }
    /**
     * 取消动画
     */
    private void cancelAnimator() {
        if (resetXAnimator != null && resetXAnimator.isRunning()) {
            resetXAnimator.cancel();
        }
        if (resetYAnimator != null && resetYAnimator.isRunning()) {
            resetYAnimator.cancel();
        }
        if (resetScaleAnimator != null && resetScaleAnimator.isRunning()) {
            resetScaleAnimator.cancel();
        }
    }

}
