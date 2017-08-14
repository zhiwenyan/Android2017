package zhiwenyan.cmccaifu.com.android2017.view.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
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
 * Created by zhiwenyan on 2017/8/10.
 */

public class ProgressBar extends View {
    private int mInnerBackground;
    private int mOutreBackground;
    private int mRoundWidth;
    private int mProgressTextSize;
    private int mProgressTextColor;
    private Paint mInnerPaint;
    private Paint nOuterPaint;
    private Paint mTextPaint;
    private int mMax = 100;
    private int mProgeress = 28;


    public ProgressBar(Context context) {
        this(context, null);
    }

    public ProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar);
        mInnerBackground = typedArray.getColor(R.styleable.ProgressBar_innerBackground, ContextCompat.getColor(context, R.color.colorPrimary));
        mOutreBackground = typedArray.getColor(R.styleable.ProgressBar_outerBackground, ContextCompat.getColor(context, R.color.colorAccent));
        mRoundWidth = (int) typedArray.getDimension(R.styleable.ProgressBar_roundWidth, dip2px(10));
        mProgressTextSize = (int) typedArray.getDimension(R.styleable.ProgressBar_roundWidth, dip2sp(16));
        mProgressTextColor = typedArray.getColor(R.styleable.ProgressBar_roundWidth, Color.RED);
        typedArray.recycle();

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerBackground);
        mInnerPaint.setStrokeWidth(mRoundWidth);
        mInnerPaint.setStyle(Paint.Style.STROKE);

        nOuterPaint = new Paint();
        nOuterPaint.setAntiAlias(true);
        nOuterPaint.setColor(mOutreBackground);
        nOuterPaint.setStrokeWidth(mRoundWidth);
        nOuterPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mProgressTextSize);
        mTextPaint.setColor(mProgressTextColor);

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
        int center = getWidth() / 2;
        //画内圆
        canvas.drawCircle(center, center, center - mRoundWidth / 2, mInnerPaint);
        //画外圆 画圆弧
        RectF rectF = new RectF(mRoundWidth / 2, mRoundWidth / 2,
                getWidth() - mRoundWidth / 2, getHeight() - mRoundWidth / 2);
        if (mProgeress == 0) return;
        float percent = (float) mProgeress / 100;
        canvas.drawArc(rectF, 0, percent * 360, false, nOuterPaint);

        //画文字
        String text = percent * 100 + "%";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
        int x = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom / 2;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseLine, mTextPaint);

    }

    public synchronized void setMax(int max) {
        mMax = max;
    }

    public synchronized void setProgeress(int progeress) {
        mProgeress = progeress;
        //刷新View
        invalidate();
    }


    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    private int dip2sp(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dip, getResources().getDisplayMetrics());
    }
}
