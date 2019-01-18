package zhiwenyan.cmccaifu.com.android2017.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import zhiwenyan.cmccaifu.com.android2017.eventbus.EventBus;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        EventBus.getDefault().post("service");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
