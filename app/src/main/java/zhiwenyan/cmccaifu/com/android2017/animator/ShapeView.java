package zhiwenyan.cmccaifu.com.android2017.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiwenyan on 2017/8/10.
 */

public class ShapeView extends View {
    private Paint mPaint;
    private Shape mCurrentShape = Shape.Circle;
    private Path mPath = null;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
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
        switch (mCurrentShape) {
            case Circle:
                //画圆形
                mPaint.setColor(Color.BLUE);
                int center = getWidth() / 2;
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                //画正方形
                mPaint.setColor(Color.RED);
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                //画三角  Path 画路线
                mPaint.setColor(Color.GREEN);
                if (mPath == null) {
                    mPath = new Path();
                    mPath.moveTo(getWidth() / 2, 0);
                    mPath.lineTo(0, (float) ((getWidth() / 2) * Math.sqrt(3)));
                    mPath.lineTo(getWidth(), (float) ((getWidth() / 2) * Math.sqrt(3)));
                    // path.lineTo(getWidth()/2, 0);
                    mPath.close(); //闭合
                }
                canvas.drawPath(mPath, mPaint);
                break;
        }
    }

    public void exchange() {
        switch (mCurrentShape) {
            case Circle:
                mCurrentShape = Shape.Square;
                break;
            case Square:
                mCurrentShape = Shape.Triangle;
                break;
            case Triangle:
                mCurrentShape = Shape.Circle;
                break;
        }
        invalidate();

    }

    public enum Shape {
        Circle, Square, Triangle
    }

    public Shape getCurrentShape() {
        return mCurrentShape;
    }
}
