package zhiwenyan.cmccaifu.com.android2017.ViewGroup.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zhiwenyan on 2017/8/8.
 */

public class TouchViewGroup extends LinearLayout {
    public TouchViewGroup(Context context) {
        this(context, null);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("TAG", "ViewGroup-->dispatchTouchEvent: " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("TAG", "ViewGroup-->onInterceptTouchEvent: " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }


//    I/TAG: ViewGroup-->dispatchTouchEvent: 0
//    I/TAG: ViewGroup-->onInterceptTouchEvent: 0
//    I/TAG: View-->dispatchTouchEvent: 0
//    I/TAG: tv-->OnTouchListener: 0
//    I/TAG: View-->onTouchEvent: 0
//    I/TAG: ViewGroup-->dispatchTouchEvent: 1
//    I/TAG: ViewGroup-->onInterceptTouchEvent: 1
//    I/TAG: View-->dispatchTouchEvent: 1
//    I/TAG: tv-->OnTouchListener: 1
//    I/TAG: View-->onTouchEvent: 1
//    I/TAG: tv-->onClick:


//    I/TAG: ViewGroup-->dispatchTouchEvent: 0
//    I/TAG: ViewGroup-->onInterceptTouchEvent: 0
//    I/TAG: View-->dispatchTouchEvent: 0
//    I/TAG: tv-->OnTouchListener: 0
//    I/TAG: View-->onTouchEvent: 0
//    I/TAG: ViewGroup-->dispatchTouchEvent: 1
//    I/TAG: ViewGroup-->onInterceptTouchEvent: 1
//    I/TAG: View-->dispatchTouchEvent: 1
//    I/TAG: tv-->OnTouchListener: 1
//    I/TAG: View-->onTouchEvent: 1
//    I/TAG: tv-->onClick:


    //    I/TAG: ViewGroup-->dispatchTouchEvent: 0
//    I/TAG: ViewGroup-->onInterceptTouchEvent: 0
//    I/TAG: View-->dispatchTouchEvent: 0
//    I/TAG: tv-->OnTouchListener: 0
//    I/TAG: View-->onTouchEvent: 0
//    I/TAG: ViewGroup-->dispatchTouchEvent: 1
//    I/TAG: ViewGroup-->onInterceptTouchEvent: 1
//    I/TAG: View-->dispatchTouchEvent: 1
//    I/TAG: tv-->OnTouchListener: 1
//    I/TAG: View-->onTouchEvent: 1
//    I/TAG: tv-->onClick:
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TAG", "ViewGroup-->onTouchEvent: " + event.getAction());
        return super.onTouchEvent(event);
    }
}
