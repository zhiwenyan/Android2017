package zhiwenyan.cmccaifu.com.android2017.view.yahoo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhiwenyan.cmccaifu.com.android2017.R;

public class YahooActivity extends AppCompatActivity {
    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yahoo);
        mLoadingView = (LoadingView) findViewById(R.id.loadView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingView.disappear();
            }
        }, 2888);
    }
}
