package zhiwenyan.cmccaifu.com.android2017.view.wave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.view.customview.WaveView1;

public class WaveViewActivity extends AppCompatActivity {
    private WaveView mWaveView;
    private WaveView1 mWaveView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_view);
        mWaveView = (WaveView) findViewById(R.id.waveView);
        mWaveView.setProgress(100, 10000);
        mWaveView1 = (WaveView1) findViewById(R.id.waveView1);
        mWaveView1.setProgress(100, 10000);
    }
}
