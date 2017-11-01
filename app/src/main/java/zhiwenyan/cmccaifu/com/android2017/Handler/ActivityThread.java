package zhiwenyan.cmccaifu.com.android2017.Handler;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class ActivityThread {
    private H mH = new H();

    public void attach(boolean b) {
        Activity mainActivity = new MainActivity();
        mainActivity.onCreate();
        //通过Handler去通知Activity的生命周期
        Message1 message = new Message1();
        message.obj = mainActivity;
        mH.sendMessage(message);
    }

    private class H extends Handler1 {
        public void handlerMessage(Message1 msg) {

        }

        public void sendMessage(Message1 msg) {
            Activity mainActivity = (Activity) msg.obj;
            mainActivity.onResume();
        }
    }
}
