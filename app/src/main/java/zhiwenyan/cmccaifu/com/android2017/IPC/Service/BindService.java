package zhiwenyan.cmccaifu.com.android2017.IPC.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {
    public BindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("-----", "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

   public class MyBinder extends Binder {
        public void startDownLoad() {
            Log.i("---", "startDownLoad: ");
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("---", "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("---", "onDestroy: ");

    }
}
