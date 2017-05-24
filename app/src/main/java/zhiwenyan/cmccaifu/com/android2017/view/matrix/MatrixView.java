package zhiwenyan.cmccaifu.com.android2017.view.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zhiwenyan on 4/28/17.
 */

public class MatrixView extends android.support.v7.widget.AppCompatImageView {
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private Context mContext;
    private Paint mPaint;

    public MatrixView(Context context) {
        this(context, null);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void setImageResource(int resId) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        // mPaint.setStyle(Paint.Style.FILL);
        mBitmap = ((BitmapDrawable) mContext.getResources().getDrawable(resId)).getBitmap();

    }

    //缩放
    protected void setScaleBitmap(float x, float y) {
        // 初始化Matrix对象
        Bitmap afterBitmap = Bitmap.createBitmap((int) (this.getWidth() * y),
                (int) (this.getHeight() * x),
                mBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        mMatrix = new Matrix();
        mMatrix.postScale(x,y);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        // 根据传入的参数设置缩放比例
        this.setImageBitmap(afterBitmap);
    }

    //平移
    protected void setTranslateBitmap() {
        // 初始化Matrix对象
        Bitmap afterBitmap = Bitmap.createBitmap(mBitmap.getWidth() * 2, mBitmap.getHeight() * 2,
                mBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        mMatrix = new Matrix();
        mMatrix.setTranslate(100, 100);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        // 根据传入的参数设置缩放比例
        this.setImageBitmap(afterBitmap);
    }

    //旋转
    protected void setRorationBitmap() {
        // 初始化Matrix对象
        Bitmap afterBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(),
                mBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        mMatrix = new Matrix();
        mMatrix.setRotate(45f);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        // 根据传入的参数设置缩放比例
        this.setImageBitmap(afterBitmap);
    }

    //倾斜
    protected void setSkewBitmap() {
        // 初始化Matrix对象
        Bitmap afterBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(),
                mBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        mMatrix = new Matrix();
        mMatrix.setSkew(5f, 5f);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        // 根据传入的参数设置缩放比例
        this.setImageBitmap(afterBitmap);
    }
}
