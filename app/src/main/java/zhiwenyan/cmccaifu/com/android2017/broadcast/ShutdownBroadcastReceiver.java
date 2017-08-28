package zhiwenyan.cmccaifu.com.android2017.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 *
 */
public class ShutdownBroadcastReceiver extends BroadcastReceiver {
    private static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SHUTDOWN)) {
        }
    }
}
