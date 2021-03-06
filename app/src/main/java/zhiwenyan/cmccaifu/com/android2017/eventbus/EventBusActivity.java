package zhiwenyan.cmccaifu.com.android2017.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.activity.MainActivity;

public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        EventBus.getDefault().register(this);
        findViewById(R.id.eventBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventBusActivity.this, MainActivity.class));
            }
        });
    }

    /**
     * threadMode
     * priority
     * sticky
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 50, sticky = true)
    public void test(String msg) {
        Log.i("EventBusActivity", "test: " + msg);
    }

    /**
     * threadMode
     * priority
     * sticky
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 50, sticky = true)
    public void test1(String msg) {
        Log.i("EventBusActivity", "test: " + msg);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
