package zhiwenyan.cmccaifu.com.android2017.view.password;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Description:
 * Data：3/9/2018-1:59 PM
 *
 * @author: yanzhiwen
 */
public class SuccessPayView extends View {
    private Paint mPaint;
    private int mRadius;
    private int mColor = Color.BLUE;
    private int mStrokeWidth = px2dip(4);

    public SuccessPayView(Context context) {
        this(context, null);
    }

    public SuccessPayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuccessPayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
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
        drawCircle(canvas);
        drawSuccessPayView(canvas);
    }

    private void drawCircle(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        mRadius = width / 2;
        canvas.drawCircle(width / 2, height / 2, mRadius - mStrokeWidth / 2, mPaint);
    }

    private void drawSuccessPayView(Canvas canvas) {
        Path path = new Path();
        int width = getWidth();
        int height = getHeight();
        //四分之三处height * 3 / 4
        path.moveTo(50, height * 3 / 4);
        path.lineTo(width / 2, height);
        //四分之一处height * 1 / 4
        path.lineTo(width - 50, height / 4);
        canvas.drawPath(path, mPaint);
    }

    private int px2dip(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px,
                getResources().getDisplayMetrics());
    }
}
