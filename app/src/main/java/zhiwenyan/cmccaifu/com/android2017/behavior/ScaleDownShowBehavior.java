
package zhiwenyan.cmccaifu.com.android2017.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class ScaleDownShowBehavior extends FloatingActionButton.Behavior {

    private boolean isAnimateIng = false;   // 是否正在动画
    private boolean isShow = true;  // 是否已经显示


    //需要带参数的AttributeSet因为XML需要调用
    public ScaleDownShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 刚刚解析Behavior，会调用这个方法
     *
     * @param lp
     */
    @Override
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams lp) {
        super.onAttachedToLayoutParams(lp);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild,
                                       View target, int nestedScrollAxes) {
        //上下滑动
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        //监测RecyclerView移动而移动（被观察者）
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);

    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        // 手指上滑，隐藏Fab
        if ((dyConsumed > 0 || dyUnconsumed > 0) && !isAnimateIng && isShow) {
            translateHide(child, new StateListener() {
                @Override
                public void onAnimationStart(View view) {
                    super.onAnimationStart(view);
                    isShow = false;
                }
            });
        } else if ((dyConsumed < 0 || dyUnconsumed < 0 && !isAnimateIng && !isShow)) {
            // 手指下滑，显示Fab
            translateShow(child, new StateListener() {
                @Override
                public void onAnimationStart(View view) {
                    super.onAnimationStart(view);
                    isShow = true;
                }
            });
        }
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

    }

    // 显示view
    public void translateShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .translationY(0)
                .setDuration(400)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(new LinearOutSlowInInterpolator())
                .start();
    }

    // 隐藏view
    public void translateHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .translationY(260)
                .setDuration(400)
                .setInterpolator(new LinearOutSlowInInterpolator())
                .setListener(viewPropertyAnimatorListener)
                .start();
    }

    class StateListener implements ViewPropertyAnimatorListener {
        @Override
        public void onAnimationStart(View view) {
            isAnimateIng = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimateIng = false;
        }

        @Override
        public void onAnimationCancel(View view) {
            isAnimateIng = false;
        }
    }


}