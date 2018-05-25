package zhiwenyan.cmccaifu.com.android2017.eventbus.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * EventBus还支持发送黏性事件。何为黏性事件呢？简单讲，就是在发送事件之后再订阅该事件也能收到该事件，跟黏性广播类似。具体用法如下：
 */
public class StickyModeActivity extends AppCompatActivity {
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_mode);
        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("test" + index++));
            }
        });
        findViewById(R.id.regist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(StickyModeActivity.this);
            }
        });

        findViewById(R.id.unregist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().unregister(StickyModeActivity.this);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEventPostThread(MessageEvent messageEvent) {
        Log.e("PostThread", messageEvent.message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEventMainThread(MessageEvent messageEvent) {
        Log.e("MainThread", messageEvent.message);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void onMessageEventBackgroundThread(MessageEvent messageEvent) {
        Log.e("BackgroundThread", messageEvent.message);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    public void onMessageEventAsync(MessageEvent messageEvent) {
        Log.e("Async", messageEvent.message);
    }
}
