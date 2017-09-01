package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/9/1.
 */

public class RunView extends View {
    private Paint mInnerPaint;
    private Paint mTextPaint;
    private Paint mOuterPaint;
    private int mBorderWidth = dip2px(6);
    private int mCurrentStep;
    private int mMaxStep;

    public RunView(Context context) {
        this(context, null);
    }

    public RunView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RunView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setDither(true);
        mInnerPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(sp2px(16));
        mTextPaint.setDither(true);
        mTextPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setDither(true);
        mOuterPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        mOuterPaint.setStrokeWidth(mBorderWidth);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOuterPaint.setStyle(Paint.Style.STROKE);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawInnerArc(canvas);
        drawStepText(canvas);
        drawOuterArc(canvas);
    }


    /**
     * 画内圆弧
     *
     * @param canvas
     */
    private void drawInnerArc(Canvas canvas) {
        RectF rectf = new RectF(mBorderWidth / 2, mBorderWidth / 2,
                getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);
        canvas.drawArc(rectf, 135, 270, false, mInnerPaint);

    }

    /**
     * 画中间的文字
     *
     * @param canvas
     */
    private void drawStepText(Canvas canvas) {
        String stepText = mCurrentStep + "步";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        float dx = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(stepText, dx, baseLine, mTextPaint);
    }

    /**
     * 画外圆弧
     *
     * @param canvas
     */
    private void drawOuterArc(Canvas canvas) {
        if (mMaxStep == 0) return;

        RectF rectf = new RectF(mBorderWidth / 2, mBorderWidth / 2,
                getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);
        float sweepAngle = ((float) mCurrentStep / mMaxStep) * 270;
        canvas.drawArc(rectf, 135, sweepAngle, false, mOuterPaint);

    }

    public void setCurrentStep(int currentStep) {
        mCurrentStep = currentStep;
        invalidate();
    }

    public int getMaxStep() {
        return mMaxStep;
    }

    public void setMaxStep(int maxStep) {
        this.mMaxStep = maxStep;
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }

    private int sp2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px, getResources().getDisplayMetrics());
    }
}
