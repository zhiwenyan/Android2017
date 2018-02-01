package zhiwenyan.cmccaifu.com.android2017.view.switchButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：1/30/2018-3:46 PM
 *
 * @author: yanzhiwen
 */
public class StepViewGroup extends LinearLayout {

    public StepViewGroup(Context context) {
        this(context, null);
    }

    public StepViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StepViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepViewGroup, defStyleAttr, 0);
        int count = array.getIndex(R.styleable.StepViewGroup_count);
        array.recycle();
        addStepCircleView(count);
    }

    private void addStepCircleView(int count) {
        for (int i = 0; i <= count; i++) {
            StepCircleView stepCircleView = new StepCircleView(getContext());
//            stepCircleView.setLayoutParams();
            addView(stepCircleView);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
