package zhiwenyan.cmccaifu.com.android2017.animator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

public class WubaCityActivity extends AppCompatActivity {
    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuba_city);
        mLoadingView = (LoadingView) findViewById(R.id.loadView);
        mLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }
}
