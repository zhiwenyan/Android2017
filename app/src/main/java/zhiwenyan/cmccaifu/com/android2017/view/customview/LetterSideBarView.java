package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhiwenyan on 6/5/17.
 * <p>
 * 自定义索引
 */

public class LetterSideBarView extends View {
    private Paint mPaint;
    private String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String mTouchLetter;
    private boolean mCurrentIsTouch;

    public LetterSideBarView(Context context) {
        this(context, null);
    }


    public LetterSideBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(px2sp(14));
        mPaint.setColor(Color.BLACK);
    }

    protected int px2sp(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float singleHeight = (float) getHeight() / mLetters.length;
        for (int i = 0; i < mLetters.length; i++) {
//
//            int x = (int) (getWidth() - paint.measureText(strs[i])) / 2;
//            int y = getHeight() / strs.length * (i + 1);
//            canvas.drawText(strs[i], x, y, paint);
            // 每个字母所占用的高度
            // 不断循环把绘制字母
            String letter = mLetters[i];
            // 获取字体的宽度
            float measureTextWidth = mPaint.measureText(letter);
            // 获取内容的宽度
            int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
            float x = getPaddingLeft() + (contentWidth - measureTextWidth) / 2;
            // 计算基线位置
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float baseLine = singleHeight / 2 + (singleHeight * i) +
                    (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            // 画字母，后面onTouch的时候需要处理高亮
            if (mLetters[i].equals(mTouchLetter)) {
                mPaint.setTextSize(px2sp(18));
                mPaint.setColor(Color.RED);
                canvas.drawText(letter, x, baseLine, mPaint);
            } else {
                mPaint.setTextSize(px2sp(14));
                mPaint.setColor(Color.BLACK);
                canvas.drawText(letter, x, baseLine, mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //计算出当前触摸的字母，获取当前的位置
                float currentMoveY = (int) event.getY();
                //位置=currentMoveY/字母的高度
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
                int currentPosition = (int) currentMoveY / itemHeight;
                if (currentPosition < 0) {
                    currentPosition = 0;
                }
                if (currentPosition > mLetters.length - 1) {
                    currentPosition = mLetters.length - 1;
                }
                //
                mTouchLetter = mLetters[currentPosition];
                mCurrentIsTouch = true;
                if (mTouchListener != null) {
                    mTouchListener.onTouch(mTouchLetter, mCurrentIsTouch);
                }
                break;
            case MotionEvent.ACTION_UP:
                mCurrentIsTouch = false;
                if (mTouchListener != null) {
                    mTouchListener.onTouch(mTouchLetter, mCurrentIsTouch);
                }
                break;
        }
        invalidate();

        return true;
    }

    // 设置触摸监听
    private SideBarTouchListener mTouchListener;

    public void setOnSideBarTouchListener(SideBarTouchListener touchListener) {
        this.mTouchListener = touchListener;
    }

    public interface SideBarTouchListener {
        void onTouch(String letter, boolean isTouch);
    }
}
