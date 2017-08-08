package zhiwenyan.cmccaifu.com.android2017.view.touchView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zhiwenyan on 2017/8/7.
 * <p>
 * ViewGroup的事件分发
 */

public class TouchViewGroup extends LinearLayout {

    public TouchViewGroup(Context context) {
        this(context,null);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("TAG", "ViewGroup--->dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    //事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("TAG", "ViewGroup--->onInterceptTouchEvent: " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    //事件触摸
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TAG", "ViewGroup--->onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
