package zhiwenyan.cmccaifu.com.android2017.view.switchButton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：1/30/2018-3:49 PM
 *
 * @author: yanzhiwen
 */
public class StepCircleView extends View {
    private Paint mCirclePaint;
    private int mCircleColor;
    private Paint mTextPaint;
    private String mText = "2";

    public StepCircleView(Context context) {
        this(context, null);
    }

    public StepCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCircleColor = ContextCompat.getColor(getContext(), R.color.common_fm_color);
        mCirclePaint.setColor(mCircleColor);
        mTextPaint = new Paint();
        mTextPaint.setDither(true);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(px2sp(16));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(widthMeasureSpec);
        int height = measureSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }


    private int measureSize(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) px2dp(40);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawTextView(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2, mCirclePaint);
    }

    private void drawTextView(Canvas canvas) {
        //获取字体的宽度
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), bounds);
        int x = getMeasuredWidth() / 2 - bounds.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getMeasuredHeight() / 2 + dy;
        canvas.drawText(mText, x, baseLine, mTextPaint);
    }

    public void setText(String text) {
        this.mText = text;
        invalidate();
    }

    public void setCircleColor(int circleColor) {
        mCircleColor = circleColor;
        invalidate();
    }

    private float px2sp(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());

    }

    private float px2dp(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());

    }
}
