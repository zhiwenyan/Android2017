package zhiwenyan.cmccaifu.com.android2017.view.textView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 6/12/17.
 */

public class ColorTrackTextView extends android.support.v7.widget.AppCompatTextView {
    // 默认的字体颜色的画笔
    private Paint mOriginPaint;
    // 改变的字体颜色的画笔
    private Paint mChangePaint;
    // 当前的进度
    private float mCurrentProgress = 0f;

    // 当前文本
    private String mText;

    //实现不同的朝向
    private Direction mDirection;


    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        int originColor = array.getColor(R.styleable.ColorTrackTextView_originColor, getTextColors().getDefaultColor());
        int changeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, getTextColors().getDefaultColor());
        array.recycle();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mOriginPaint = getPaintByColor(Color.BLACK);
        mChangePaint = getPaintByColor(Color.RED);
    }

    /**
     * 获取画笔根据不同的颜色
     */
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        // 抗锯齿
        paint.setAntiAlias(true);
        // 仿抖动
        paint.setDither(true);
        paint.setColor(color);
        // 字体的大小设置为TextView的文字大小
        paint.setTextSize(getTextSize());
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    //一个字体两种颜色
    //利用clipRect的Api,可以裁剪
    @Override
    protected void onDraw(Canvas canvas) {
        int middle = (int) (mCurrentProgress * getWidth());
        if (mDirection == Direction.LEFT_TO_RIGHT) {
            drawText(canvas, mChangePaint, 0, middle);
            drawText(canvas, mOriginPaint, middle, getWidth());
        } else {
            drawText(canvas, mChangePaint, getWidth() - middle, getWidth());
            drawText(canvas, mOriginPaint, 0, getWidth() - middle);
        }
    }

    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        //绘制不变色
        Rect rect = new Rect(start, 0, end, getHeight());
        //利用clipRect的Api,可以裁剪
        canvas.clipRect(rect);
        //super.onDraw(canvas);
        String text = getText().toString();
        //获取字体的宽度
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = getWidth() / 2 - bounds.width() / 2;
        //基线baseLine
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        //绘制
        canvas.drawText(text, x, baseLine, paint);
        canvas.restore();
    }

    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    public void setChangeColor(int changeColor) {
        mChangePaint.setColor(changeColor);
    }

    public void setOriginColor(int originColor) {
        mOriginPaint.setColor(originColor);
    }
}