package zhiwenyan.cmccaifu.com.android2017.view.coupon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/**
 * Created by zhiwenayn on 2017/8/18.
 */

public class CouponView extends RelativeLayout {
    private Paint mPaint;


    public CouponView(Context context) {
        this(context, null);
    }

    public CouponView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(0, getHeight() / 2, getHeight() / 4, mPaint);
    }


    private int dip2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }
}
