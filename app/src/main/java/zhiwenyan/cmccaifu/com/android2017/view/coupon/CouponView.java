package zhiwenyan.cmccaifu.com.android2017.view.coupon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;


/**
 * Description: 优惠劵
 * Data：12/19/2017-5:00 PM
 *
 * @author: yanzhiwen
 */
public class CouponView extends RelativeLayout {
    private Paint mPaint;
    private int mRadius;
    private int mGap;
    private int mCircleNum;
    private int mRemain;

    public CouponView(Context context) {
        this(context, null);
    }

    public CouponView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mRadius = DensityUtil.px2dip(getContext(), 24);
        mGap = DensityUtil.px2dip(getContext(), 18);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mRemain == 0) {
            //计算不整除的剩余部分
            mRemain = (w - mGap * 2) % (2 * mRadius + mGap);
        }
        mCircleNum = (w - mGap * 2) / (2 * mRadius + mGap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mCircleNum; i++) {
            float cy = mGap + mRadius + ((mGap + mRadius * 2) * i)+mRemain;
            canvas.drawCircle(0, cy, mRadius, mPaint);
            canvas.drawCircle(getMeasuredWidth(), cy, mRadius, mPaint);
        }
    }
}
