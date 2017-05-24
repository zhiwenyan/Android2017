package zhiwenyan.cmccaifu.com.android2017.banner.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class CameraTestView extends View {
    private Camera mCamera;
    private Matrix mMatrix;
    private Paint mPaint;


    public CameraTestView(Context context) {
        this(context, null);
    }

    public CameraTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCamera = new Camera();
        mMatrix = new Matrix();
        setBackgroundColor(Color.parseColor("#3f51b5"));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#ff4081"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(60,60,60,mPaint);
    }
}
