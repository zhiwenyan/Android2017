package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/9/1.
 */

public class RotationLineView extends View {
    private Paint mCirclePaint;
    private Paint mLinePaint;
    private int mCricleRadius;
    private Paint mPaint;
    private float mMaxAngle = 300f;
    private float mChangleAngle = 0f;
    private float mHasDraw;
    private boolean mIsForward = true;

    public RotationLineView(Context context) {
        this(context, null);
    }

    public RotationLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotationLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setDither(true);
        mLinePaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        mLinePaint.setStrokeWidth(dip2px(1));
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = Math.min(width, height);
        mCricleRadius = widthSize / 2;
        setMeasuredDimension(widthSize, widthSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF oval = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawArc(oval, 135, 270, false, mPaint);
        drawRotationLine(canvas);
        drawCircle(canvas);
    }

    private void drawRotationLine(Canvas canvas) {
        canvas.save();
        canvas.translate(mCricleRadius, mCricleRadius); //将画布的原点平移
        canvas.rotate(30);
        float hasDraw = 0f;
        float rotationAngle = mMaxAngle / 100f;
        for (int i = 0; i < 100; i++) {
            if (mChangleAngle != 0 && hasDraw <= mChangleAngle) {
                canvas.drawLine(0, mCricleRadius, 0, mCricleRadius - dip2px(20), mLinePaint);
                canvas.rotate(rotationAngle);
            }
            hasDraw += rotationAngle;
        }
        //操作完成后恢复状态
        canvas.restore();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - dip2px(25), mCirclePaint);

    }

    private boolean mRunning;

    public void changeAngle() {
        if (mRunning) {
            return;
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mIsForward) {
                    if (mChangleAngle < mMaxAngle) {
                        mChangleAngle += 3;
                    } else {
                        mIsForward = false;
                    }
                } else {
                    mChangleAngle -= 3;
                }
                postInvalidate();
            }
        }, 500, 30);
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                px, getResources().getDisplayMetrics());
    }
}
