package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

public class CustomActivity extends AppCompatActivity {
    private HuaWeiView mHuaWeiView;
    private QQRunView mQQRunView;
    private TextView mTextView;
    private RunView mRunView;
    private RotationLineView mRotationLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        mRotationLineView = (RotationLineView) findViewById(R.id.LineView);
        mRunView = (RunView) findViewById(R.id.runView);
        mHuaWeiView = (HuaWeiView) findViewById(R.id.hw);
        mHuaWeiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHuaWeiView.changeAngle(300);
            }
        });
        mQQRunView = (QQRunView) findViewById(R.id.stepView);
        mQQRunView.setStepMax(3000);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(6000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mQQRunView.setCurrentStep((int) value);
            }
        });
        valueAnimator.start();

        mRunView.setMaxStep(1000);
        ValueAnimator animator = ValueAnimator.ofFloat(0, mRunView.getMaxStep());
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                mRunView.setCurrentStep((int) currentValue);

            }
        });
        animator.start();
        mRotationLineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRotationLineView.changeAngle();

            }
        });


//        mTextView = (TextView) findViewById(R.id.tv);
//        Log.e("TAG", "onCreate: " + mTextView.getMeasuredHeight());
//        mTextView.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("TAG", "post: " + mTextView.getMeasuredHeight());
//
//            }
//        });
    }
}
