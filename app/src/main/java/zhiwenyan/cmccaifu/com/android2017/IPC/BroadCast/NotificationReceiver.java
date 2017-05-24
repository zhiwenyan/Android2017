package zhiwenyan.cmccaifu.com.android2017.IPC.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import zhiwenyan.cmccaifu.com.android2017.IPC.ServiceActivity;
import zhiwenyan.cmccaifu.com.android2017.IPC.SystemUtils;
import zhiwenyan.cmccaifu.com.android2017.activity.MainActivity;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //判断app进程是否存活
        if (SystemUtils.isAppAlive(context, "zhiwenyan.cmccaifu.com.android2017")) {
            Log.i("NotificationReceiver", "the app process is alive");
            Intent mainIntent = new Intent(context, MainActivity.class);
            //将MainAtivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
            //如果Task栈中有MainActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
            //如果Task栈不存在MainActivity实例，则在栈顶创建
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Intent detailIntent = new Intent(context, ServiceActivity.class);
            Intent[] intents = {mainIntent, detailIntent};
            context.startActivities(intents);
        } else {
            //如果app进程已经被杀死，先重新启动app
            Log.i("NotificationReceiver", "the app process is dead");
            Intent launchIntent = context.getPackageManager().
                    getLaunchIntentForPackage("zhiwenyan.cmccaifu.com.android2017");
            launchIntent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);
        }
    }
}
