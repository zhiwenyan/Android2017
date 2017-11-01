package zhiwenyan.cmccaifu.com.android2017.Handler;


/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class HandlerTest {
    public static void main(String[] args) {
        Looper1.prepare();
        ActivityThread activityThread = new ActivityThread();
        activityThread.attach(false);
        Looper1.loop();

    }
}
