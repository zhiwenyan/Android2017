package zhiwenyan.cmccaifu.com.android2017.view.radar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：1/5/2018-3:29 PM
 *
 * @author: yanzhiwen
 */
public class RadarView extends View {
    //每个圆圈所占的比例
    private static float[] circleProportion = {1 / 12f, 2 / 12f, 3 / 12f, 4 / 12f, 5 / 12f, 6 / 12f};
    private int mWidth;
    private int mHeight;
    private Paint mCirclePaint;
    private Bitmap mCenterIcon;
    private Paint mPaintScan;//画扫描需要用到的paint
    private Matrix mRoteMatrix = new Matrix();//旋转需要的矩阵
    private int mRoteDegree;//扫描旋转的角度
    private Shader scanShader;//扫描渲染shader
    private boolean mIsScan;
    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mRoteDegree += 2;
            mRoteMatrix.postRotate(mRoteDegree, mWidth / 2, mHeight / 2);
            invalidate();
            postDelayed(mRunnable, 60);
        }
    };


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mPaintScan = new Paint();
        mPaintScan.setAntiAlias(true);
        mCenterIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(widthMeasureSpec));
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mWidth = mHeight = Math.min(mWidth, mHeight);
        //设置扫描渲染的shader

        scanShader = new SweepGradient(mWidth / 2, mHeight / 2,
                new int[]{Color.TRANSPARENT, Color.parseColor("#84B5CA")}, null);

    }

    private int measureSize(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 300;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRoteDegree = 0;
        drawCircle(canvas);
        drawCenterIcon(canvas);
        drawScan(canvas);
        if (!mIsScan) {
            post(mRunnable);
            mIsScan = true;
        }
    }

    /**
     * 绘制六个圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        for (int i = 0; i < circleProportion.length; i++) {
            canvas.drawCircle(mWidth / 2, mWidth / 2,
                    mWidth * circleProportion[i], mCirclePaint);
        }

    }

    private void drawCenterIcon(Canvas canvas) {
        float iconWidth = (mWidth * circleProportion[0]);
        canvas.drawBitmap(mCenterIcon, mWidth / 2 - mCenterIcon.getWidth() / 2, mHeight / 2 - mCenterIcon.getHeight() / 2, null);
    }

    /**
     * 绘制扫描
     *
     * @param canvas
     */
    private void drawScan(Canvas canvas) {
        canvas.save();
        mPaintScan.setShader(scanShader);
        canvas.concat(mRoteMatrix);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[4], mPaintScan);
        canvas.restore();
    }
}
