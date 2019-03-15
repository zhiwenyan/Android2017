package zhiwenyan.cmccaifu.com.android2017.ViewGroup.event;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description:
 * Data：2/18/2019-1:46 PM
 *
 * @author yanzhiwen
 */
public class BadViewPager extends ViewPager {
    private static final String TAG = "BadViewPager";

    private int mLastXIntercept;
    private int mLastYIntercept;


    public BadViewPager(@NonNull Context context) {
        this(context, null);
    }

    public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercepted = false;
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                intercepted = false;
//                //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
//                super.onInterceptTouchEvent(ev);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //横坐标位移增量
//                int deltaX = x - mLastXIntercept;
//                //纵坐标位移增量
//                int deltaY = y - mLastYIntercept;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    intercepted = true;
//                } else {
//                    intercepted = false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                intercepted = false;
//                break;
//            default:
//                break;
//        }
//
//        mLastXIntercept = x;
//        mLastYIntercept = y;
//
//        Log.e(TAG, "intercepted = " + intercepted);
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_DOWN) {
            super.onInterceptTouchEvent(ev);
            return false;
        }
        return true;
    }
}
