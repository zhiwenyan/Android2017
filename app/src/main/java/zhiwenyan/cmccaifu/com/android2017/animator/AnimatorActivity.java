package zhiwenyan.cmccaifu.com.android2017.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class AnimatorActivity extends BaseActivity {

    @BindView(R.id.transTv)
    Button mTransTv;
    @BindView(R.id.scaleTv)
    Button mScaleTv;
    @BindView(R.id.rorateTv)
    Button mRorateTv;
    @BindView(R.id.alphaTv)
    Button mAlphaTv;

    @BindView(R.id.mTarget)
    TextView mTarget;
    @BindView(R.id.circleView)
    CircleView mCircleView;

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animator;
    }


    @OnClick({R.id.transTv, R.id.scaleTv, R.id.rorateTv, R.id.alphaTv, R.id.groupAnimTv,
            R.id.ValuesHolderTv, R.id.PropertyValuesHolderTv, R.id.keyFrameTv, R.id.simpleAnimator, R.id.wubaCityBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.transTv:
                ObjectAnimator translation = ObjectAnimator.ofFloat(mTarget, "translationY", 0f, 1000f);
                translation.setInterpolator(new LinearInterpolator());
                translation.setDuration(1500);
                translation.start();
                break;
            case R.id.scaleTv:
                ObjectAnimator scale = ObjectAnimator.ofFloat(mTarget, "scaleX", 1f, 1.5f, 1f);
                scale.setInterpolator(new BounceInterpolator());
                scale.setDuration(1500);
                scale.start();
                break;
            case R.id.rorateTv:
                ObjectAnimator rotation = ObjectAnimator.ofFloat(mTarget, "rotation", 0, 360f);
                rotation.setDuration(1500);
                rotation.start();
                break;
            case R.id.alphaTv:
                ObjectAnimator alpha = ObjectAnimator.ofFloat(mTarget, "alpha", 1.0f, 0, 1.0f);
                alpha.setDuration(1500);
                alpha.start();
                break;
            case R.id.groupAnimTv:
//                ObjectAnimator scaleObjectAnimator = ObjectAnimator.ofFloat(mTarget, "scaleX", 1f, 1.5f, 1f);
//                ObjectAnimator rotationObjectAnimator = ObjectAnimator.ofFloat(mTarget, "rotation", 0, 360f);
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.play(scaleObjectAnimator).with(rotationObjectAnimator);
//                animatorSet.play(scaleObjectAnimator).after(rotationObjectAnimator);
//                animatorSet.setDuration(2000);
//                animatorSet.start();
                AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set);
                animatorSet.setDuration(3000);
                animatorSet.setTarget(mTarget);
                animatorSet.start();
                break;
            case R.id.ValuesHolderTv:
                int startColor = 0xff00b2f9;
                int centerColor = 0xffffff00;
                int endColor = 0xff00b2f9;
                ValueAnimator valueAnimator = ValueAnimator.ofArgb(startColor, centerColor, endColor);
                valueAnimator.setDuration(3000);
                valueAnimator.start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int color = (int) animation.getAnimatedValue();
                        mTarget.setBackgroundColor(color);
                    }
                });
                break;
            case R.id.PropertyValuesHolderTv:
                PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
                PropertyValuesHolder colorHolder = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xff00b2f9);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTarget, rotationHolder, colorHolder);
                animator.setDuration(3000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
                break;
            case R.id.keyFrameTv:
                //  Keyframe.ofFloat(0, 0)表示动画进度为0时，动画所在的数值位置为0；
                // Keyframe.ofFloat(0.25f, -20f)表示动画进度为25%时，动画所在的数值位置为-20
                // Keyframe.ofFloat(1f,0)表示动画结束时，动画所在的数值位置为0；
                Keyframe frame0 = Keyframe.ofFloat(0f, 0);
                Keyframe frame1 = Keyframe.ofFloat(0.25f, -20f);
                Keyframe frame2 = Keyframe.ofFloat(0.5f, 20f);
                Keyframe frame3 = Keyframe.ofFloat(1f, 0);
                PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3);
                Animator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(mTarget, frameHolder);
                ofPropertyValuesHolder.setDuration(1000);
                ofPropertyValuesHolder.start();
                break;
            case R.id.simpleAnimator:
                ValueAnimator va = new ValueAnimator();
                va.setInterpolator(new LinearInterpolator());
                va.setDuration(2000);
                va.setObjectValues(new PointF(0, 0));
                va.setEvaluator(new TypeEvaluator() {
                    @Override
                    public Object evaluate(float fraction, Object startValue, Object endValue) {
                        // x方向200px/s ，则y方向0.5 * 10 * t
                        PointF point = new PointF();
                        point.x = 200 * fraction * 3;
                        point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                        return point;
                    }
                });
                va.start();
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF point = (PointF) animation.getAnimatedValue();
                        mCircleView.setX(point.x);
                        mCircleView.setY(point.y);
                    }
                });
                break;
            case R.id.wubaCityBtn:
                startActivity(new Intent(this,WubaCityActivity.class));
                break;
        }
    }
}
