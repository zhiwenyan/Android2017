package zhiwenyan.cmccaifu.com.android2017.IPC.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import zhiwenyan.cmccaifu.com.android2017.IPC.BroadCast.NotificationReceiver;
import zhiwenyan.cmccaifu.com.android2017.R;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//获取系统服务
        Notification noti = new Notification.Builder(this)//实例化Builder
                .setTicker("通知")//在状态栏显示的标题
                //.setWhen(java.lang.System.currentTimeMillis())//设置显示的时间，默认就是currentTimeMillis()
                .setContentTitle("New mail from ")//设置标题
                .setContentText("zhiwenyan@qq.com")//设置内容
                .setSmallIcon(R.mipmap.ic_launcher)//设置状态栏显示时的图标      .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))//设置显示的大图标    .setContentIntent(PendingIntent.getActivity(MainActivity.this, 0, new Intent(Settings.ACTION_SETTINGS), 0))//设置点击时的意图
                // .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, ServiceActivity.class), 0))//设置删除时的意图             .setFullScreenIntent(PendingIntent.getActivity(MainActivity.this, 0, new Intent(Settings.ACTION_SETTINGS), 0), true)//这个将直接打开意图，而不经过状态栏显示再按下
                .setAutoCancel(false)//设置是否自动按下过后取消
                .setOngoing(true)//设置为true时就不能删除  除非使用notificationManager.cancel(1)方法
                .build();//创建Notification
        noti.contentIntent = (PendingIntent.getBroadcast(this, 0, new Intent(this, NotificationReceiver.class), 0));
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        startForeground(1, noti);//前台进程
        notificationManager.notify(1, noti);//管理器通知
        Log.i("--", "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("--", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("--", "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder {
        private void startDownLoad() {
            Log.i("--", "startDownLoad: ");
        }
    }

}
