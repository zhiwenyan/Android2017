package zhiwenyan.cmccaifu.com.android2017.view.scanloading;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ScanLoadingActivity extends AppCompatActivity {
    private ScanLoadingView mScanLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_loading);
        mScanLoadingView = (ScanLoadingView) findViewById(R.id.scanLv);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
        animator.setDuration(1888);
        animator.setRepeatCount(-1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mScanLoadingView.setStartAngle((float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
}
