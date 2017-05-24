package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhiwenyan on 5/16/17.
 */

public class HuaWeiView extends View {
    private int width;
    private Paint mPaint;
    private RectF oval;
    private float startSweep = 120f;
    private float sweepAngle = 300f;
    private int radius;
    private float targetAngle = 0f;
    private boolean isRunning;
    //判断是回退的状态还是前进状态
    private int state = 1;
    int red;
    int green;
    int blue;
    int score;


    public HuaWeiView(Context context) {
        this(context, null);
    }

    public HuaWeiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HuaWeiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        width = Math.min(widthSize, heightSize);
        oval = new RectF(0, 0, width, width);  // 绘制一个矩形区域
        radius = width / 2;
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(oval, startSweep, sweepAngle, false, mPaint);
        //画刻度线的方法
        drawViewLine(canvas);
        //绘制文本
        drawText(canvas);
        //绘制水波纹
        drawWave(canvas);
    }

    private void drawWave(Canvas canvas) {

    }

    private void drawText(Canvas canvas) {
        //先绘制一个小圆
        Paint smallPaint = new Paint();
        //  smallPaint.setARGB(100,red,green,0);
        // 画小圆指定圆心坐标，半径，画笔即可
        int smallRadius = radius - 60;
        smallPaint.setAntiAlias(true);
        smallPaint.setARGB(255, red, green, blue);
        canvas.drawCircle(radius, radius, radius - 60, smallPaint);
        //绘制文本
        Paint textPaint = new Paint();
        //设置文本居中对齐
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(smallRadius / 2);
        textPaint.setAntiAlias(true);
        //score需要通过计算得到
        canvas.drawText("" + score, radius, radius, textPaint);
        //绘制分，在分数的右上方
        textPaint.setTextSize(smallRadius / 6);
        canvas.drawText("分", radius + smallRadius / 2, radius - smallRadius / 4, textPaint);
        //绘制点击优化在分数的下方
        textPaint.setTextSize(smallRadius / 6);
        canvas.drawText("点击优化", radius, radius + smallRadius / 2, textPaint);
    }

    private void drawViewLine(Canvas canvas) {
        //先保存之前canvas的内容
        canvas.save();
        //移动canvas(X轴移动距离，Y轴移动距离)
        canvas.translate(radius, radius);
        //旋转坐标系
        canvas.rotate(30);
        Paint linePatin = new Paint();
        //设置画笔颜色
        linePatin.setColor(Color.WHITE);
        //线宽
        linePatin.setStrokeWidth(2);
        //设置画笔抗锯齿
        linePatin.setAntiAlias(true);
        //确定每次旋转的角度
        float rotateAngle = sweepAngle / 100;
        //绘制有色部分的画笔
        Paint targetLinePatin = new Paint();
        targetLinePatin.setColor(Color.GREEN);
        targetLinePatin.setStrokeWidth(2);
        targetLinePatin.setAntiAlias(true);
        //记录已经绘制过的有色部分范围
        float hasDraw = 0;
        for (int i = 0; i <= 100; i++) {
            if (hasDraw <= targetAngle && targetAngle != 0) {//需要绘制有色部分的时候
                //计算已经绘制的比例
                float percent = hasDraw / sweepAngle;
                red = 255 - (int) (255 * percent);
                green = (int) (255 * percent);
                blue = (int) (255 * percent);
                targetLinePatin.setARGB(255, red, green, blue);
                //画一条刻度线
                canvas.drawLine(0, radius, 0, radius - 40, targetLinePatin);
            } else {//不需要绘制有色部分
                //画一条刻度线
                canvas.drawLine(0, radius, 0, radius - 40, linePatin);
            }
            //累计绘制过的部分
            hasDraw += rotateAngle;
            //旋转
            canvas.rotate(rotateAngle);
        }

        //操作完成后恢复状态
        canvas.restore();
    }

    public void changeAngle(final float trueAngle) {
        if (isRunning) {//如果在动直接返回
            return;
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (state) {

                    case 1://后退状态
                        isRunning = true;

                        targetAngle -= 3;
                        score = (int) targetAngle / 3;
                        if (targetAngle <= 0) {//如果回退到0
                            targetAngle = 0;
                            //改为前进状态
                            state = 2;

                        }
                        break;
                    case 2://前进状态
                        targetAngle += 3;
                        score = (int) targetAngle / 3;
                        if (targetAngle >= trueAngle) {//如果增加到指定角度
                            targetAngle = trueAngle;
                            //改为后退状态
                            state = 1;
                            isRunning = false;
                            //结束本次运动
                            timer.cancel();
                        }
                        break;
                    default:
                        break;
                }
                //重新绘制（子线程中使用的方法）
                postInvalidate();
            }
        }, 500, 30);
    }
}
