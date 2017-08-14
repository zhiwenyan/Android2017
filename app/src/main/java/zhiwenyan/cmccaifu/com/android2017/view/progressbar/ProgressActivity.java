package zhiwenyan.cmccaifu.com.android2017.view.progressbar;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ProgressActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setTarget(mProgressBar);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = Integer.valueOf(String.valueOf(animation.getAnimatedValue()));
                mProgressBar.setProgeress(value);
            }
        });
        valueAnimator.start();
    }
}
