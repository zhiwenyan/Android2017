package zhiwenyan.cmccaifu.com.android2017.view.BezierCurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhiwenyan on 2017/8/18.
 */

public class BezierCurveView extends View {
    private PointF mPointF;
    private PointF mDragPointF;
    private Paint mPaint;
    private int mRadius = dip2px(8);
    private int mRadiusMax = dip2px(10);
    private int mRadiusMin = dip2px(4);

    public BezierCurveView(Context context) {
        this(context, null);
    }

    public BezierCurveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierCurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float downX = event.getX();
                float downY = event.getY();
                initPoint(downX, downY);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                updateDragPoint(moveX, moveY);
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPointF == null || mDragPointF == null) {
            return;
        }
        //拖拽圆
        canvas.drawCircle(mDragPointF.x, mDragPointF.y, dip2px(16), mPaint);
        //固定圆  随着距离的增大而减小
        //计算两个点的距离
        double distance = getDistance(mDragPointF, mPointF);
        mRadius = (int) (mRadiusMax - distance / 8);
        Path bezierPath = getPath();
        if (bezierPath != null) {
            canvas.drawCircle(mPointF.x, mPointF.y, mRadius, mPaint);
            canvas.drawPath(bezierPath, mPaint);
        }
    }


    /**
     * 更新拖拽点的位置
     *
     * @param moveX
     * @param moveY
     */
    private void updateDragPoint(float moveX, float moveY) {
        mDragPointF.x = moveX;
        mDragPointF.y = moveY;
    }

    private void initPoint(float downX, float downY) {
        mDragPointF = new PointF(downX, downY);
        mPointF = new PointF(downX, downY);
    }

    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }

    /**
     * 获取两个圆的距离
     *
     * @param point1
     * @param point2
     * @return
     */
    private double getDistance(PointF point1, PointF point2) {
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point2.y - point1.y) * (point2.y - point1.y));
    }

    private Path getPath() {
        double distance = getDistance(mDragPointF, mPointF);
        mRadius = (int) (mRadiusMax - distance / 8);
        if (mRadius > mRadiusMin) {
            return null;
        }
        Path path = new Path();
        //求斜率
        double dy = mDragPointF.y - mPointF.y;
        double dx = mDragPointF.x - mPointF.x;
        double tanA = dy / dx;
        //求角a

        return path;
    }
}
