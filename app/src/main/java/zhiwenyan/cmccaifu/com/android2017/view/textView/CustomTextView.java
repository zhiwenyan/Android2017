package zhiwenyan.cmccaifu.com.android2017.view.textView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by yanzhiwen on 2017/11/7.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {


    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        public static final int UNSPECIFIED = 0 << MODE_SHIFT;
//
//        /**
//         * Measure specification mode: The parent has determined an exact size
//         * for the child. The child is going to be given those bounds regardless
//         * of how big it wants to be.
//         */
//        public static final int EXACTLY     = 1 << MODE_SHIFT;
//
//        /**
//         * Measure specification mode: The child can be as large as it wants up
//         * to the specified size.
//         */
//        public static final int AT_MOST     = 2 << MODE_SHIFT;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
