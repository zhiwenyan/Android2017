package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import zhiwenyan.cmccaifu.com.android2017.R;

public class CustomActivity extends AppCompatActivity {
    private HuaWeiView mHuaWeiView;
    private QQRunView mQQRunView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
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
    }
}
