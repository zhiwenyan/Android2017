package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;

/**
 * Created by zhiwenyan on 5/24/17.
 */

public class QQRunView extends View {
    private Paint mOuterePaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;
    private int mOuterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = DensityUtil.px2dip(getContext(), 6);
    private int mTextSize = DensityUtil.px2sp(getContext(), 18);
    private int mTextColor = Color.RED;
    private int mStepMax;  //最大的步数
    private int mCurrentStep;//当前的步数


    public QQRunView(Context context) {
        this(context, null);
    }

    public QQRunView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQRunView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mOuterColor = ta.getColor(R.styleable.QQStepView_outerColor, mOuterColor);
        mInnerColor = ta.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBorderWidth = (int) ta.getDimension(R.styleable.QQStepView_borderWidth, mBorderWidth);
        mTextSize = (int) ta.getDimension(R.styleable.QQStepView_stepTextSize, mTextSize);
        mTextColor = ta.getColor(R.styleable.QQStepView_stepTextColor, mTextColor);
        ta.recycle();
        init();

    }

    private void init() {
        mOuterePaint = new Paint();
        mInnerPaint = new Paint();
        mOuterePaint.setAntiAlias(true);
        mOuterePaint.setStyle(Paint.Style.STROKE);
        mOuterePaint.setStrokeWidth(DensityUtil.px2dip(getContext(), mBorderWidth));
        mOuterePaint.setColor(mOuterColor);
        mOuterePaint.setStrokeCap(Paint.Cap.ROUND); //圆角
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeWidth(DensityUtil.px2dip(getContext(), mBorderWidth));
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int center = getWidth() / 2;
//        int radius = getHeight() / 2 - mBorderWidth / 2;
//        RectF rectF = new RectF(center - radius, center - radius,
//                center + radius, center + radius);
        //画外圆弧
        RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2, getWidth() - mBorderWidth / 2,
                getHeight() - mBorderWidth / 2);
        canvas.drawArc(rectF, 135, 270, false, mOuterePaint);
        //画内圆弧
        if (mStepMax == 0) return;
        RectF rectF1 = new RectF(mBorderWidth / 2, mBorderWidth / 2, getWidth() - mBorderWidth / 2,
                getHeight() - mBorderWidth / 2);
        float sweepAngle = (float) mCurrentStep / mStepMax;
        canvas.drawArc(rectF1, 135, sweepAngle * 270, false, mInnerPaint);

        //画文字
        String stepText = mCurrentStep + "步";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        int dx = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(stepText, dx, baseLine, mTextPaint);
    }


    public synchronized void setStepMax(int stepMax) {
        this.mStepMax = stepMax;
    }

    public synchronized void setCurrentStep(int currentStep) {
        this.mCurrentStep = currentStep;
        //不断绘制
        invalidate();
    }
}
