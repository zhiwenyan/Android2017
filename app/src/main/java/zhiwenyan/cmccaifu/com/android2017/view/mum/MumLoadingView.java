package zhiwenyan.cmccaifu.com.android2017.view.mum;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：11/6/2018-1:50 PM
 *
 * @author yanzhiwen
 */
public class MumLoadingView extends View {
    private Paint mLinePaint;
    private Paint mTextPaint;
    private int mLineColor;
    private int mLineRotationColor;
    private int mWidth;
    private int mHeight;
    private int mLineWidth;
    private int mTextSize;
    private int mTextColor;
    private int mLineRotationAngle;
    private int mCurrentProgress;
    private int mCurrentRotationAngle;
    private boolean mIsLoading;

    public MumLoadingView(Context context) {
        this(context, null);
    }

    public MumLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MumLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();

    }

    private void initPaint() {
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MumLoadingView);
        mLineColor = array.getColor(R.styleable.MumLoadingView_lineColor, Color.BLACK);
        mLineRotationColor = array.getColor(R.styleable.MumLoadingView_lineRotationColor, Color.RED);
        mLineWidth = ( int ) array.getDimension(R.styleable.MumLoadingView_lineWidth, dp2px(8));
        mTextSize = ( int ) array.getDimension(R.styleable.MumLoadingView_textSize, sp2px(16));
        mTextColor = array.getColor(R.styleable.MumLoadingView_textColor, Color.RED);
        mLineRotationAngle = array.getInteger(R.styleable.MumLoadingView_lineRotationAngle, 5);
        array.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        drawText(canvas);
    }

    private void drawLine(Canvas canvas) {
        canvas.save();
        //将画布移动到中心的位置
        canvas.translate(mWidth / 2, mHeight / 2);
        if (mIsLoading) {
            for (int i = 0; i <= mCurrentRotationAngle; i += mLineRotationAngle) {
                mLinePaint.setColor(mLineRotationColor);
                canvas.drawLine(0, mHeight / 2 - 30, 0, mHeight / 2, mLinePaint);
                canvas.rotate(mLineRotationAngle);
            }
        }
        for (int i = mCurrentRotationAngle; i < 360; i += mLineRotationAngle) {
            mLinePaint.setColor(mLineColor);
            canvas.drawLine(0, mHeight / 2 - 30, 0, mHeight / 2, mLinePaint);
            canvas.rotate(mLineRotationAngle);
        }
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        String progressText = mCurrentProgress + "%";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), textBounds);
        int dx = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(progressText, dx, baseLine, mTextPaint);
    }

    public void startAnimation() {
        if (mIsLoading) {
            return;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mIsLoading = true;
                float value = ( float ) animation.getAnimatedValue();
                setCurrentProgress(( int ) value);
                setCurrentRotationAngle(( int ) value);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void setCurrentProgress(int currentProgress) {
        this.mCurrentProgress = currentProgress;
    }

    public void setCurrentRotationAngle(int currentRotationAngle) {
        this.mCurrentRotationAngle = ( int ) (currentRotationAngle * 3.6f);
    }

    public void dismiss() {
        clearAnimation();
        mIsLoading = false;
    }

    private int dp2px(int dp) {
        return ( int ) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return ( int ) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
