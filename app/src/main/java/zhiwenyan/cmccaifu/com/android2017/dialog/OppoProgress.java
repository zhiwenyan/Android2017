package zhiwenyan.cmccaifu.com.android2017.dialog;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：11/17/2017-2:57 PM
 * Author: yanzhiwen
 */
public class OppoProgress extends View {
    private Paint mPaint;
    private int mArcWidth;

    public OppoProgress(Context context) {
        this(context, null);
    }

    public OppoProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OppoProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mArcWidth = dip2px(2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mPaint.setStrokeWidth(mArcWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = mArcWidth / 2;
        canvas.drawArc(width, width, getWidth() - width, getHeight() - width,
                30, 120, false, mPaint);


//        canvas.drawArc(width, width, getWidth() - width, getHeight() - width,
//                120, 120, false, mPaint);

//
        canvas.drawArc(width, width, getWidth() - width, getHeight() - width,

                210, 120, false, mPaint);
    }

    private int dip2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    public void startAnimation() {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,360);
        valueAnimator.setDuration(888);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value=(float)animation.getAnimatedValue();
                OppoProgress.this.setRotation(value);
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
