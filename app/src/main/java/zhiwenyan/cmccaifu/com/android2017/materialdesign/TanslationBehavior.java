package zhiwenyan.cmccaifu.com.android2017.materialdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hcDarren on 2017/7/10.
 */

public class TanslationBehavior extends FloatingActionButton.Behavior {
    public TanslationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 关注垂直滚动 ，而且向上的时候是出来，向下是隐藏
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child,
                                       @NonNull View directTargetChild, @NonNull View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    private boolean isOut = false;

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.e("TAG", "dyConsumed -> " + dyConsumed + " dyUnconsumed -> " + dyUnconsumed);
        // 而且向上的时候是出来，向下是隐藏
        if (dyConsumed > 0) {
            if (!isOut) {
                // 往上滑动，是隐藏 , 加一个标志位 已经往下走了
                int translationY = ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).bottomMargin + child.getMeasuredHeight();
                child.animate().translationY(translationY).setDuration(500).start();
                isOut = true;
            }
        } else {
            if (isOut) {
                // 往下滑动
                child.animate().translationY(0).setDuration(500).start();
                isOut = false;
            }
        }
    }
}
