package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/9/1.
 */

public class WaveView extends View {
    private Paint mCirclePaint;
    private Paint mWavePaint;
    private int mRadius;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mWavePaint = new Paint();
        mWavePaint.setAntiAlias(true);
        mWavePaint.setDither(true);
        mWavePaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(widthSize, heightSize);
        mRadius = size / 2;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawWave(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mCirclePaint);
    }

    private void drawWave(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, mRadius);
        path.quadTo(mRadius / 2, mRadius / 2, mRadius, mRadius);
        path.moveTo(mRadius, mRadius);
        path.quadTo(mRadius + mRadius / 2, mRadius / 2 + mRadius, getWidth(), mRadius);
        path.close();
        canvas.drawPath(path, mWavePaint);
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }

}
