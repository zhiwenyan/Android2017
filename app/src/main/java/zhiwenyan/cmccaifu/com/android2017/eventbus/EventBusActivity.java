package zhiwenyan.cmccaifu.com.android2017.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import zhiwenyan.cmccaifu.com.android2017.R;

public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        EventBus.getDefault().register(this);
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

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
