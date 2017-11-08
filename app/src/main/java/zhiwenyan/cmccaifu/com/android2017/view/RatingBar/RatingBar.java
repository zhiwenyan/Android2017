package zhiwenyan.cmccaifu.com.android2017.view.RatingBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/11/7.
 */

public class RatingBar extends View {
    private Bitmap mNormalBitmap;
    private Bitmap mFocusBitmap;
    private int mStarNum = 5;
    private int mCurrentNum;


    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        int statNormalId = typedArray.getResourceId(R.styleable.RatingBar_starNormal, 0);
        int statFocusId = typedArray.getResourceId(R.styleable.RatingBar_starFocus, 0);
        if (statNormalId == 0) {
            throw new RuntimeException("");
        }
        mNormalBitmap = BitmapFactory.decodeResource(getResources(), statNormalId);
        if (statFocusId == 0) {
            throw new RuntimeException("");
        }
        mFocusBitmap = BitmapFactory.decodeResource(getResources(), statFocusId);

        mStarNum = typedArray.getInt(R.styleable.RatingBar_starNum, 5);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //高度
        int height = mNormalBitmap.getHeight();
        //宽度
        int width = mNormalBitmap.getWidth() * mStarNum * getPaddingLeft();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mStarNum; i++) {
            if (mCurrentNum > i) {
                canvas.drawBitmap(mFocusBitmap, (mFocusBitmap.getWidth() + getPaddingLeft()) * i, 0, null);
            } else {
                canvas.drawBitmap(mNormalBitmap, (mNormalBitmap.getWidth() + getPaddingLeft()) * i, 0, null);

            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                int currentNum = (int) (moveX / mNormalBitmap.getWidth() + 1);
                if (currentNum < 0) {
                    currentNum = 1;
                }
                if (currentNum > mStarNum) {
                    currentNum = mStarNum;
                }
                if (mCurrentNum == currentNum) {
                    return true;
                }
                mCurrentNum = currentNum;
                invalidate();
                break;
        }
        return true;
    }
}
