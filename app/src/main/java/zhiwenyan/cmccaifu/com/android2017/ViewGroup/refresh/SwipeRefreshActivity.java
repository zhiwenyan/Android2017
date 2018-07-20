package zhiwenyan.cmccaifu.com.android2017.ViewGroup.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class SwipeRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshMultiStatusLayout mRefreshMultiStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        mRefreshMultiStatusLayout = findViewById(R.id.swipe_refresh);
        mRefreshMultiStatusLayout.setRefreshing(true);
        mRefreshMultiStatusLayout.setOnRefreshListener(this);
        onRequestData();
    }

    private void onRequestData() {
        new Handler().postDelayed(() -> {
            mRefreshMultiStatusLayout.setRefreshing(false);
            mRefreshMultiStatusLayout.showEmptyView();
        }, 2000);
    }

    @Override
    public void onRefresh() {
        onRequestData();
    }
}
