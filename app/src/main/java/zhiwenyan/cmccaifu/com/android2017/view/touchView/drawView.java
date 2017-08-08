package zhiwenyan.cmccaifu.com.android2017.view.touchView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiwenyan on 2017/8/7.
 * <p>
 * View的绘制流程
 */

public class drawView extends View {

    public drawView(Context context) {
        super(context);
    }

    public drawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public drawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
