package zhiwenyan.cmccaifu.com.android2017.IPC;

import android.content.Intent;

import zhiwenyan.cmccaifu.com.android2017.IPC.Service.MyService;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ServiceActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    //    Intent intent = new Intent(this, BindService.class);
      //  bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

//    ServiceConnection mServiceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            BindService.MyBinder myBinder = (BindService.MyBinder) service;
//            myBinder.startDownLoad();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbindService(mServiceConnection);
//    }
}
