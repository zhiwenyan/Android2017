package zhiwenyan.cmccaifu.com.android2017.cl;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;

public class ClCircleActivity extends AppCompatActivity {
    private FloatingActionButton mFabAdd;
    private FloatingActionButton mFabLike;
    private FloatingActionButton mFabWrite;
    private FloatingActionButton mFabTop;
    // 动画集合，用来控制动画的有序播放
    private AnimatorSet animatorSet;
    // 圆的半径
    private int radius;
    // FloatingActionButton宽度和高度，宽高一样
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl_circle);
        mFabAdd = findViewById(R.id.fab_add);
        mFabLike = findViewById(R.id.fab_like);
        mFabWrite = findViewById(R.id.fab_write);
        mFabTop = findViewById(R.id.fab_top);

        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFabLike.setVisibility(View.GONE);
            }
        });

        // 动态获取FloatingActionButton的宽
        mFabAdd.post(new Runnable() {
            @Override
            public void run() {
                width = mFabAdd.getMeasuredWidth();
            }
        });
        // 在xml文件里设置的半径
        radius = DensityUtil.dp2px(80);
        setViewVisible(false);
        initListener();
    }

    private void setViewVisible(boolean isShow) {
        mFabLike.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mFabWrite.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mFabTop.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void initListener() {
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 播放动画的时候不可以点击
                if (animatorSet != null && animatorSet.isRunning())
                    return;

                // 判断播放显示还是隐藏动画
                if (mFabLike.getVisibility() != View.VISIBLE) {
                    animatorSet = new AnimatorSet();
                    ValueAnimator likeAnimator = getValueAnimator(mFabLike, false, 0);
                    ValueAnimator writeAnimator = getValueAnimator(mFabWrite, false, 45);
                    ValueAnimator topAnimator = getValueAnimator(mFabTop, false, 90);
                    animatorSet.playSequentially(likeAnimator, writeAnimator, topAnimator);
                    animatorSet.start();
                } else {
                    animatorSet = new AnimatorSet();
                    ValueAnimator likeAnimator = getValueAnimator(mFabLike, true, 0);
                    ValueAnimator writeAnimator = getValueAnimator(mFabWrite, true, 45);
                    ValueAnimator topAnimator = getValueAnimator(mFabTop, true, 90);
                    animatorSet.playSequentially(topAnimator, writeAnimator, likeAnimator);
                    animatorSet.start();
                }

            }
        });
    }

    private ValueAnimator getValueAnimator(final FloatingActionButton fab, boolean reverse, final int angle) {

        ValueAnimator animator;
        if (reverse) {
            animator = ValueAnimator.ofFloat(1, 0);
        } else {
            animator = ValueAnimator.ofFloat(0, 1);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) fab.getLayoutParams();
                params.circleRadius = (int) (radius * v);
                //params.circleAngle = 270f + angle * v;
                params.width = (int) (width * v);
                params.height = (int) (width * v);
                fab.setLayoutParams(params);
            }
        });
        animator.addListener(new SimpleAnimation() {
            @Override
            public void onAnimationStart(Animator animation) {
                fab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(reverse){
                    fab.setVisibility(View.GONE);
                }
            }
        });
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        return animator;
    }

    abstract class SimpleAnimation implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }
}
