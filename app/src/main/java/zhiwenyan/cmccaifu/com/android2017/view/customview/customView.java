package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhiwenyan on 5/12/17.
 */

public class customView extends View {
    private Paint mPaint;

    public customView(Context context) {
        this(context, null);
    }

    public customView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public customView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("customView", "onMeasure: " + heightMode + "," + heightSize);
        if (heightMode == MeasureSpec.AT_MOST) { //wrap_content
            Log.i("customView", "onMeasure: " + "MeasureSpec.AT_MOST");
        }
        if (heightMode == MeasureSpec.EXACTLY) { //100dp match_parent fill_parent
            Log.i("customView", "onMeasure: " + "MeasureSpec.EXACTLY");
        }
        if (heightMode == MeasureSpec.UNSPECIFIED) {  //尽可能的大，很少用到
            Log.i("customView", "onMeasure: " + "MeasureSpec.UNSPECIFIED");
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
