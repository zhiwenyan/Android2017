package zhiwenyan.cmccaifu.com.android2017.view.CustomProgressBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 5/2/17.
 */

public class CustomProgressBar extends View {
    private Paint mPaint;
    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;
    private int mSpeed;
    private int mProgress;
    private boolean isNext;

    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomProgressBar, defStyleAttr, 0);
        mFirstColor = array.getColor(R.styleable.CustomProgressBar_firstColor, Color.GREEN);
        mSecondColor = array.getColor(R.styleable.CustomProgressBar_secondColor, Color.RED);
        mCircleWidth = array.getDimensionPixelSize(R.styleable.CustomProgressBar_circleWidth, 10);
        mSpeed = array.getInt(R.styleable.CustomProgressBar_speed, 20);// 默认20

        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE); //空心
        mPaint.setStrokeWidth(mCircleWidth);

        // 绘图线程
        new Thread() {
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        isNext = !isNext;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取圆心的x坐标
        int centre = getWidth() / 2;
        // 半径
        int radius = centre - mCircleWidth;
        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, true, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, true, mPaint); // 根据进度画圆弧
        }
    }
}
