package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by yanzhiwen on 2017/10/18.
 */

public class WaveView1 extends View {
    /*sin曲线画笔*/
    private Paint mSinPaint;
    private Point mStartPoint;
    private int mHeight;
    private Path mSinPath;
    //sin曲线 1/4周期个宽度
    private int mCycle;
    private int mWidth;
    //sin 曲线振幅的高度
    private int mWaveHeight = 100;
    private int mProgress;

    public WaveView1(Context context) {
        this(context, null);
    }

    public WaveView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSinPath = new Path();
        mSinPaint = new Paint();
        mSinPaint.setAntiAlias(true);
        mSinPaint.setStrokeWidth(5);
        mSinPaint.setStyle(Paint.Style.FILL);
        mSinPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCycle = mWidth / 4;
        //默认从屏幕外先绘制3/4个周期 使得波峰在中间
        mStartPoint = new Point(-mCycle * 4, mHeight / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //   mStartPoint.y = (int) (mHeight - (mProgress / 100.0) * mHeight);

        mSinPath.moveTo(mStartPoint.x, mStartPoint.y);
        int j = 1;
        for (int i = 1; i <= mWidth / mCycle; i++) {
            if (i % 2 == 0) {
                //波峰
                mSinPath.quadTo(mStartPoint.x + (mCycle * j), mStartPoint.y + mWaveHeight,
                        mStartPoint.x + (mCycle * 2) * i, mStartPoint.y);
            } else {
                //波谷
                mSinPath.quadTo(mStartPoint.x + (mCycle * j), mStartPoint.y - mWaveHeight,
                        mStartPoint.x + (mCycle * 2) * i, mStartPoint.y);
            }
            j += 2;
        }
        //绘制封闭的曲线
        mSinPath.lineTo(mWidth, mHeight);//右下角
        mSinPath.lineTo(mStartPoint.x, mHeight);//左下角
        mSinPath.lineTo(mStartPoint.x, mStartPoint.y);//起点
        mSinPath.close();
        canvas.drawPath(mSinPath, mSinPaint);

        if (mStartPoint.x + mCycle / 4 >= 0) {
            //满了一个周期则恢复默认起点继续平移
            mStartPoint.x = -mCycle * 4;
        }
        //每次移动1/4个周期
        mStartPoint.x += mCycle / 4;
        postInvalidateDelayed(200);
        mSinPath.reset();
    }


    public void setProgress(final int progress, int duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, progress);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                WaveView1.this.mProgress = (int) value;
                invalidate();
            }
        });
        valueAnimator.start();
    }


    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }
}
