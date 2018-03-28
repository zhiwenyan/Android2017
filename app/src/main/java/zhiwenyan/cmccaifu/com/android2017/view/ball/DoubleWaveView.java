package zhiwenyan.cmccaifu.com.android2017.view.ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * Data：3/22/2018-9:31 AM
 *
 * @author: yanzhiwen
 */
public class DoubleWaveView extends View {
    private Paint mPaint;
    private float count;
    private int offsetX;
    //波浪的高度
    private int mWaveHeight = 100;
    private Path mWavePath;

    public DoubleWaveView(Context context) {
        this(context, null);
    }

    public DoubleWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mWavePath = new Path();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWavePath.moveTo(0, 0);
        mWavePath.rQuadTo(getWidth() / 0.25f, mWaveHeight, getWidth() / 0.5f, 0);
     //   mWavePath.rQuadTo(getWidth() * 0.75f, -mWaveHeight, getWidth(), 0);
        mWavePath.close();
        canvas.drawPath(mWavePath, mPaint);
    }
}
