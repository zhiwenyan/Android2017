package zhiwenyan.cmccaifu.com.android2017.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhiwenyan.cmccaifu.com.android2017.activity.INetEvent;


public abstract class BaseActivity extends AppCompatActivity implements INetEvent {
    private Unbinder mUnbinder;
    public static INetEvent mINetEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initToolBar();

        //初始化网络状态的监听
        mINetEvent = this;
//        Intent intent = new Intent(this, NetStateReceiver.class);
//        intent.setAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        sendBroadcast(intent);

        init();


    }

    protected void initToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            doSetToolBarTitle("");
        }
    }

    protected void init() {
    }

    protected void doSetToolBarTitle(String title) {
        getSupportActionBar().setTitle(title);

    }

    protected abstract int getLayoutId();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    /**
     * 全局检测网络广播的回调 处理网络变化
     * 注:在程序第一次启动的时候,没网并不会回调,需要自己在启动页面,或者主页自己再判断一次
     *
     * @param netWorkState 网络状态    -1:没网络 0:移动网络 1:WiFi网络
     */
    public void onNetChanged(int netWorkState) {
        Log.i("TAG", "onNetChanged: " + netWorkState);
    }

    @Override
    public void onNetChange(int netWorkState) {
        onNetChanged(netWorkState);
    }
}
