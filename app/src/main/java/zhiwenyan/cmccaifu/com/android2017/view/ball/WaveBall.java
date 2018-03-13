package zhiwenyan.cmccaifu.com.android2017.view.ball;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;


/**
 * Description:
 * Data：3/8/2018-1:24 PM
 *
 * @author: yanzhiwen
 */
public class WaveBall extends View {
    private String mCenterText;
    private float mCenterTextSize;
    private int mCenterTextColor;
    private int mBallColor;
    private float mRadius;
    private Paint mFontPaint;
    private Paint mRoundPaint;
    private Paint mProgressPaint;
    private int mWaveColor;
    private int mMaxProgress = 100;
    private int mCurrentProgress = 0;
    //每次上移的距离
    private int mSpace = 30;
    //每次累加上移的距离
    private int mMove = 0;

    public WaveBall(Context context) {
        this(context, null);
    }

    public WaveBall(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveBall);
        mCenterText = typedArray.getString(R.styleable.WaveBall_centerText);
        mCenterTextSize = typedArray.getDimension(R.styleable.WaveBall_centerTextSize, 24f);
        mCenterTextColor = typedArray.getColor(R.styleable.WaveBall_centerTextColor, 0xFFFFFF);
        mBallColor = typedArray.getColor(R.styleable.WaveBall_ballColor, 0xFF4081);
        mRadius = typedArray.getDimension(R.styleable.WaveBall_ballRadius, 260f);
        mWaveColor = typedArray.getColor(R.styleable.WaveBall_waveColor, 0x00C6FF);
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        mRoundPaint = new Paint();
        mRoundPaint.setAntiAlias(true);
        mRoundPaint.setDither(true);
        mRoundPaint.setColor(mBallColor);
        mFontPaint = new Paint();
        mFontPaint.setTextSize(mCenterTextSize);
        mFontPaint.setColor(mCenterTextColor);
        mFontPaint.setAntiAlias(true);
        //粗体
        mFontPaint.setFakeBoldText(true);
        mProgressPaint = new Paint();
        mProgressPaint.setColor(mWaveColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setDither(true);
        //取两层绘制交集。显示上层
        mProgressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量规格大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = (int) Math.min(widthSize, mRadius * 2);
        } else {
            width = 300;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = (int) Math.min(heightSize, mRadius * 2);
        } else {
            height = 300;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawCenterText(canvas);
        drawWave(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mRoundPaint);

    }

    private void drawCenterText(Canvas canvas) {
        mCenterText = mCurrentProgress + "%";
        Rect textBounds = new Rect();
        mFontPaint.getTextBounds(mCenterText, 0, mCenterText.length(), textBounds);
        int dx = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = mFontPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(mCenterText, dx, baseLine, mFontPaint);
    }

    private void drawWave(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        //初始化画笔后，就开始绘制我们的图形，先初始化一个
        //宽和高都为mRadius * 2的正方形画布作为缓冲区画布，我们可以先在缓冲区画布绘制，绘制完成后一次再绘制到画布上。
        Bitmap bitmap = Bitmap.createBitmap((int) mRadius * 2, (int) mRadius * 2, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);
        Path path = new Path();
        int count = height / mSpace;
        //然后绘制圆心（width / 2, height / 2）半径为mRadius的圆
        bitmapCanvas.drawCircle(width / 2, height / 2, mRadius, mRoundPaint);
        float y = (1 - (float) mCurrentProgress / mMaxProgress) * mRadius * 2 + height / 2 - mRadius;
        mMove += 20;
        if (mMove > height) {
            mMove = height;
        }
        path.moveTo(-width + y, y);
        float d = (1 - (float) mCurrentProgress / mMaxProgress) * mSpace;
        for (int i = 0; i < count; i++) {
            path.rQuadTo(mSpace, -d, mSpace * 2, 0);
            path.rQuadTo(mSpace, d, mSpace * 2, 0);
        }
        path.lineTo(width, y);
        path.lineTo(width, height);
        path.lineTo(0, height);
        //path.close();将所画区域封闭，也就是实心的效果。
        path.close();
        bitmapCanvas.drawPath(path, mProgressPaint);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    public void setCurrentProgress(float currentProgress) {
        mCurrentProgress = (int) currentProgress;
        invalidate();
    }

    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }
    };

}
