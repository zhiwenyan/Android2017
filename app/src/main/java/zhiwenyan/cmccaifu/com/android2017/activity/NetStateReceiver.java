package zhiwenyan.cmccaifu.com.android2017.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class NetStateReceiver extends BroadcastReceiver {
    private INetEvent mINetEvent= BaseActivity.mINetEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //容错机制
            if(mINetEvent!=null) {
                mINetEvent.onNetChange(NetUtils.getNetWorkState(context));
            }
        }
    }
}
