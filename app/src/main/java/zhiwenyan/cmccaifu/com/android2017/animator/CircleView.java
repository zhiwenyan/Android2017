package zhiwenyan.cmccaifu.com.android2017.animator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;


public class CircleView extends View {

    private Paint mPaint;
    private PointF currentPoint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
    }

    /**
     * 小球动画
     */
    public void startAnimationMotion() {
        PointF startPoint = new PointF(getWidth() / 2, getHeight() / 2);
        PointF endPoint = new PointF(getWidth() - getWidth() / 2, 0);
        ValueAnimator animator = ValueAnimator.ofObject(new OscillationEvaluator(), startPoint, endPoint);
        animator.setDuration(7000).setRepeatCount(3);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = ( PointF ) animation.getAnimatedValue();
                invalidate();
            }
        });
        //设置插值器
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

    }

    public PointF getCurrentPoint() {
        return currentPoint;
    }

    /**
     * sin 函数
     */
    class OscillationEvaluator implements TypeEvaluator {
        @Override
        public PointF evaluate(float fraction, Object startValue, Object endValue) {

            PointF startPoint = ( PointF ) startValue;
            PointF endPoint = ( PointF ) endValue;
            //x坐标线性变化
            float x = startPoint.x + fraction * (endPoint.x - startPoint.x);
            //y坐标取相对应函数值
            float y = 120 * ( float ) (Math.sin(0.01 * Math.PI * x)) + getHeight() / 2;
            return new PointF(x, y);
        }

    }
}