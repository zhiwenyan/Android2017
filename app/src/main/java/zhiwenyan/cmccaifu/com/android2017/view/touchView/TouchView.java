package zhiwenyan.cmccaifu.com.android2017.view.touchView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhiwenyan on 6/13/17.
 * <p>
 * View的Touch事件分发
 */

public class TouchView extends View {
    public TouchView(Context context) {
        this(context,null);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        Log.i("TAG", "View--->dispatchTouchEvent: " + event.getAction());
//        return super.dispatchTouchEvent(event);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TAG", "View--->onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }
}
