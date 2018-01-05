package zhiwenyan.cmccaifu.com.android2017.view.live;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

public class LiveActivity extends AppCompatActivity {
    private LoveLayout mLoveLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mLoveLayout=(LoveLayout)findViewById(R.id.loveLayout);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoveLayout.addLove();
            }
        });
    }
}
