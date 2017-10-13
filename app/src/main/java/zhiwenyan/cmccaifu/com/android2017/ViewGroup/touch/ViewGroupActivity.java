package zhiwenyan.cmccaifu.com.android2017.ViewGroup.touch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group2);


//        findViewById(tv).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("TAG", "tv-->OnTouchListener: " + event.getAction());
//                return false;
//            }
//        });

//        findViewById(tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("TAG", "tv-->onClick: ");
//            }
//        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.i("TAG", "Activity-->dispatchTouchEvent: " + ev.getAction());
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("TAG", "Activity-->onTouchEvent: " + event.getAction());
//        return super.onTouchEvent(event);
//    }

    /**
     * 实现onInterceptTouchEvent方法可以用来拦截父ViewGroup传递下来的所有触屏事件，
     * 可以将所有触屏事件交由此ViewGroup自身的onTouchEvent来处理，也可以继续传递给其子View来处理。

     onInterceptTouchEvent方法对触屏事件的拦截处理需要和onTouchEvent方法配合使用。

     down事件首先传递到onInterceptTouchEvent方法中

     onInterceptTouchEvent返回false表示将down事件交由子View来处理；若某一层子View的onTouchEvent返回了true，
     后续的move、up等事件都将先传递到ViewGroup的onInterceptTouchEvent的方法，并继续层层传递下去，交由子View处理；
     若子View的onTouchEvent都返回了false，则down事件将交由该ViewGroup的onTouchEvent来处理；
     如果ViewGroup的onTouchEvent返回false，down传递给父ViewGroup，后续事件不再传递给该ViewGroup；
     如果ViewGroup的onTouchEvent返回true，后续事件不再经过该ViewGroup的onInterceptTouchEvent方法，直接传递给onTouchEvent方法处理

     onInterceptTouchEvent返回ture，down事件将转交该ViewGroup的onTouchEvent来处理；若onTouchEvent返回true，
     后续事件将不再经过该ViewGroup的onInterceptTouchEvent方法，直接交由该ViewGroup的onTouchEvent方法处理；
     若onTouchEvent方法返回false，后续事件都将交由父ViewGroup处理，不再经过该ViewGroup的onInterceptTouchEvent方法和onTouchEvent方法

     看来onInterceptTouchEvent的ACTION_MOVE事件不执行的原因就是子view的down事件返回了fasle，然后ViewGroup的onTouchEvent 的down事件返回false，
     后续事件不再传递给该ViewGroup了。。
     */
}
