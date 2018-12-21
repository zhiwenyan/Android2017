package zhiwenyan.cmccaifu.com.android2017.view.mum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

public class MumLoadingViewActivity extends AppCompatActivity {
    private MumLoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_view2);
        mLoadingView=findViewById(R.id.loadingView);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingView.startAnimation();
            }
        });
    }
}
