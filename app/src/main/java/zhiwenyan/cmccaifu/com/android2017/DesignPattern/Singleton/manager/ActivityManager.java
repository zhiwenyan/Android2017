package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by yanzhiwen on 2017/10/26.
 */

public class ActivityManager {
    private static volatile ActivityManager mInstance;
    private Stack<Activity> mActivities;

    private ActivityManager() {
        mActivities = new Stack<>();
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    public void attach(Activity activity) {
        mActivities.add(activity);
    }

    public void detach(Activity detachActivity) {
        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity == detachActivity) {
                mActivities.remove(activity);
                i--;
                size--;
            }
        }
    }

    public void finish(Class<? extends Activity> activityClass) {
        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity.getClass().getCanonicalName().equals(activityClass.getCanonicalName())) {
                mActivities.remove(activity);
                activity.finish();
                i--;
                size--;
            }
        }
    }

    public void exitApplication() {

    }

    public Activity currentActivity() {
        return mActivities.lastElement();
    }
}
