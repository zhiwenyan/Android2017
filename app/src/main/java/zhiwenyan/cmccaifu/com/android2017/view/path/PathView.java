package zhiwenyan.cmccaifu.com.android2017.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiwenyan on 4/27/17.
 */

public class PathView extends View {
    private Paint mPaintXY;
    private Paint mPaint;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

        mPaintXY = new Paint();
        mPaintXY.setAntiAlias(true);
        mPaintXY.setStyle(Paint.Style.STROKE);
        mPaintXY.setColor(Color.RED);
        mPaintXY.setStrokeWidth(6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制X,Y
        canvas.drawLine(0, 0, getWidth(), 0, mPaintXY);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaintXY);


        canvas.translate(getWidth() / 2, 0);
        //绘制Line
        Path linePath = new Path();
        linePath.lineTo(200, 200);  //绘制Line
        linePath.moveTo(200, 100); //移动下一次操作的起点位置
        linePath.lineTo(200, 0);
        canvas.drawPath(linePath, mPaint);

        //canvas.restore();
        canvas.translate(0, getHeight() / 2);
        Path rectPath = new Path();
        RectF rectF = new RectF(-100, -100, 100, 100);
        rectPath.addRect(rectF, Path.Direction.CW);
       // rectPath.setLastPoint(-200, 200);    // <-- 重置最后一个点的位置
        rectPath.close();
        canvas.drawPath(rectPath, mPaint);

    }
}
