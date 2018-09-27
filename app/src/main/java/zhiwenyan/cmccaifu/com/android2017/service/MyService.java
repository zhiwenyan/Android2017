package zhiwenyan.cmccaifu.com.android2017.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import zhiwenyan.cmccaifu.com.android2017.activity.MainActivity;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
