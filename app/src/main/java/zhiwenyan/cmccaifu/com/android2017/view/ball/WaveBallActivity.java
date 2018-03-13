package zhiwenyan.cmccaifu.com.android2017.view.ball;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class WaveBallActivity extends AppCompatActivity {
    private WaveBall mWaveBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_ball);
        mWaveBall = findViewById(R.id.waveBall);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(5888);
        valueAnimator.addUpdateListener(animation -> mWaveBall.setCurrentProgress((float) animation.getAnimatedValue()));
        valueAnimator.start();
    }
}
