package zhiwenyan.cmccaifu.com.android2017.view.customview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

public class CustomActivity extends AppCompatActivity {
    private HuaWeiView mHuaWeiView;
    private QQRunView mQQRunView;
    private TextView mTextView;

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
        mTextView = (TextView) findViewById(R.id.tv);
        Log.e("TAG", "onCreate: " + mTextView.getMeasuredHeight());
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "post: " + mTextView.getMeasuredHeight());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "onResume: " + mTextView.getMeasuredHeight());

    }
}
