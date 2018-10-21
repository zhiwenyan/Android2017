package zhiwenyan.cmccaifu.com.android2017.view.sin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class WaveActivity extends AppCompatActivity {
    private WaveView mWaveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        mWaveView = findViewById(R.id.waveView);
        mWaveView.post(new Runnable() {
            @Override
            public void run() {
                mWaveView.startAnimation();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveView.startAnimation();
    }
}
