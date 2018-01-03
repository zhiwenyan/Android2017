package zhiwenyan.cmccaifu.com.android2017.view.textView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ColorTrackTextViewActivity extends AppCompatActivity {
    private ColorTrackTextView mColorTrackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_track_text_view);
        mColorTrackTextView = (ColorTrackTextView) findViewById(R.id.tv);
        findViewById(R.id.leftToRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColorTrackTextView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(2000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float currentProgress = (float) animation.getAnimatedValue();
                        mColorTrackTextView.setCurrentProgress(currentProgress);
                    }
                });
                valueAnimator.start();
            }
        });

        findViewById(R.id.rightToLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColorTrackTextView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(2000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float currentProgress = (float) animation.getAnimatedValue();
                        mColorTrackTextView.setCurrentProgress(currentProgress);
                    }
                });
                valueAnimator.start();
            }
        });
        findViewById(R.id.vp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColorTrackTextViewActivity.this,ViewPagerActivity.class));
            }
        });


    }
}
