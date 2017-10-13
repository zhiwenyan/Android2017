package zhiwenyan.cmccaifu.com.android2017.view.scanloading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;

/**
 * Created by yanzhiwen on 2017/10/9.
 */

public class ScanLoadingView extends View {
    private Paint mCirclePaint;
    private int mCircleColor = Color.parseColor("#cecece");
    private int mCircleWidth = DensityUtil.dp2px(4);
    private Paint mArcPaint;
    private int mArcColor = Color.parseColor("#999999");
    private Paint mTextPaint;
    private float mStartAngle;

    public ScanLoadingView(Context context) {
        this(context, null);
    }

    public ScanLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScanLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeWidth(mCircleWidth);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);

        mArcPaint = new Paint();
        mArcPaint.setColor(mArcColor);
        mArcPaint.setStrokeWidth(mCircleWidth);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);
        mArcPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(DensityUtil.sp2px(getContext(), 12));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(widthSize, heightSize), Math.min(widthSize, heightSize));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawArc(canvas);
        drawText(canvas);
    }


    /**
     * 画圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        int center = getMeasuredWidth() / 2;
        canvas.drawCircle(center, center, center - mCircleWidth / 2, mCirclePaint);
    }

    /**
     * 画弧
     *
     * @param canvas
     */
    private void drawArc(Canvas canvas) {
        RectF rectf = new RectF(mCircleWidth / 2, mCircleWidth / 2,
                getMeasuredWidth() - mCircleWidth / 2, getMeasuredHeight() - mCircleWidth / 2);
        canvas.drawArc(rectf, mStartAngle, 45, false, mArcPaint);
    }

    /**
     * 画文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        String text = "Loading...";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dx = getMeasuredWidth() / 2 - textBounds.width() / 2;
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getMeasuredHeight() / 2 + dy;
        canvas.drawText(text, dx, baseLine, mTextPaint);
    }

    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
        invalidate();
    }
}
