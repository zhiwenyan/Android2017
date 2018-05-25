package zhiwenyan.cmccaifu.com.android2017.eventbus.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import zhiwenyan.cmccaifu.com.android2017.R;

public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus3);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("postEvent", Thread.currentThread().getName());
                EventBus.getDefault().post(new MessageEvent("Hello EventBus"));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
//
//    在EventBus的事件处理函数中需要指定线程模型，即指定事件处理函数运行所在的想线程。
//    在上面我们已经接触到了EventBus的四种线程模型。那他们有什么区别呢？
//    在EventBus中的观察者通常有四种线程模型，分别是PostThread（默认）、MainThread、BackgroundThread与Async。
//
//    POSTING：如果使用事件处理函数指定了线程模型为PostThread，那么该事件在哪个线程发布出来的，
//    事件处理函数就会在这个线程中运行，也就是说发布事件和接收事件在同一个线程。
//    在线程模型为PostThread的事件处理函数中尽量避免执行耗时操作，因为它会阻塞事件的传递，甚至有可能会引起ANR。
//    MAIN：如果使用事件处理函数指定了线程模型为MainThread，那么不论事件是在哪个线程中发布出来的，
//    该事件处理函数都会在UI线程中执行。该方法可以用来更新UI，但是不能处理耗时操作。
//    BACKGROUND：如果使用事件处理函数指定了线程模型为BackgroundThread，那么如果事件是在UI线程中发布出来的，
//    那么该事件处理函数就会在新的线程中运行，如果事件本来就是子线程中发布出来的，那么该事件处理函数直接在发布事件的线程中执行。
//    在此事件处理函数中禁止进行UI更新操作。
//    ASYNC：如果使用事件处理函数指定了线程模型为Async，那么无论事件在哪个线程发布，该事件处理函数都会在新建的子线程中执行。
//    同样，此事件处理函数中禁止进行UI更新操作。

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEventPostThread(MessageEvent messageEvent) {
        Log.e("PostThread", Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(MessageEvent messageEvent) {
        Log.e("MainThread", Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEventBackgroundThread(MessageEvent messageEvent) {
        Log.e("BackgroundThread", Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEventAsync(MessageEvent messageEvent) {
        Log.e("Async", Thread.currentThread().getName());
    }

    //postEvent: main
    //MainThread: main
    //Async: pool-1-thread-2
    //PostThread: main
    //BackgroundThread: pool-1-thread-1


}
