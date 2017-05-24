package zhiwenyan.cmccaifu.com.android2017.IPC;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import zhiwenyan.cmccaifu.com.android2017.IPC.Service.MessengerService;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

/**
 * Messenger可以在不同进程中传递Message对象，
 * 我们在Message中加入我们想要传的数据就可以在进程间的进行数据传递了
 * Messenger是一种轻量级的IPC
 */
public class MessengerActivity extends BaseActivity {
    private Messenger mMessenger;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_FROMCLIENT:
                    Log.i("--", "收到服务端信息-------" + msg.getData().get("rep"));
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ipc;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            Message mMessage = Message.obtain(null, MessengerService.MSG_FROMCLIENT);
            Bundle mBundle = new Bundle();
            mBundle.putString("msg", "这里是客户端，收到了嘛服务端");
            mMessage.setData(mBundle);
            //将Messenger传递给服务端
            mMessage.replyTo = new Messenger(mHandler);
            try {
                mMessenger.send(mMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
