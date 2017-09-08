package zhiwenyan.cmccaifu.com.android2017.view.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/9/7.
 */

public abstract class GraphView extends View {
    private String mGraphTitle;
    private String mXAxisName;
    private String mYAxisName;
    private float mAxisTextSize;
    private int mAxisTextColor;

    private Paint mPaint;

    public int mWidth;
    public int mHeight;

    public int originalX = 100;
    public int originalY = 1000;

    public GraphView(Context context) {
        this(context, null);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GraphView);
        mGraphTitle = typedArray.getString(R.styleable.GraphView_graphTitle);
        mXAxisName = typedArray.getString(R.styleable.GraphView_xAxisName);
        mYAxisName = typedArray.getString(R.styleable.GraphView_yAxisName);
        mAxisTextSize = typedArray.getFloat(R.styleable.GraphView_axisTextSize, sp2px(14));
        mAxisTextColor = typedArray.getColor(R.styleable.GraphView_axisTextColor, Color.parseColor("#000000"));
        typedArray.recycle();
        initPaint();

    }

    private void initPaint() {
        if (mPaint == null)
            mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth() - originalX * 2;
        mHeight = (originalY > getHeight() ? getHeight() : originalY) - 400;
        drawXAxis(canvas, mPaint);
        drawYAxis(canvas, mPaint);
        drawGraphTitle(canvas);
        drawXAxisScale(canvas, mPaint);
        drawYAxisScale(canvas, mPaint);
        drawXAxisScaleValue(canvas, mPaint);
        drawYAxisScaleValue(canvas, mPaint);
        drawXAxisArrow(canvas);
        drawYAxisArrow(canvas);
        drawCloumn(canvas, mPaint);

    }

    protected void drawGraphTitle(Canvas canvas) {
        if (!TextUtils.isEmpty(mGraphTitle)) {
            mPaint.setFakeBoldText(true);
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setColor(mAxisTextColor);
            canvas.drawText(mGraphTitle, (getWidth() / 2) - (mPaint.measureText(mGraphTitle) / 2),
                    originalY + 40, mPaint);
        }
    }


    public abstract void drawXAxis(Canvas canvas, Paint paint);

    public abstract void drawYAxis(Canvas canvas, Paint paint);

    public abstract void drawXAxisScale(Canvas canvas, Paint paint);

    public abstract void drawYAxisScale(Canvas canvas, Paint paint);


    public abstract void drawXAxisScaleValue(Canvas canvas, Paint paint);

    public abstract void drawYAxisScaleValue(Canvas canvas, Paint paint);

    /**
     * 箭头
     *
     * @param canvas
     */
    private void drawXAxisArrow(Canvas canvas) {
        Path path = new Path();
        path.moveTo(originalX + mWidth + 30, originalY);
        path.lineTo(originalX + mWidth, originalY + 10);
        path.lineTo(originalX + mWidth, originalY - 10);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.drawText("天", originalX + mWidth, originalY + 50, mPaint);

    }

    /**
     * 箭头
     *
     * @param canvas
     */
    private void drawYAxisArrow(Canvas canvas) {
        Path path = new Path();
        path.moveTo(originalX, originalY - mHeight - 30);
        path.lineTo(originalX - 10, originalY - mHeight);
        path.lineTo(originalX + 10, originalY - mHeight);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.drawText("销量", originalX - 50, originalY - mHeight - 30, mPaint);

    }

    public abstract void drawCloumn(Canvas canvas, Paint paint);


    private int sp2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px, getResources().getDisplayMetrics());
    }
}
