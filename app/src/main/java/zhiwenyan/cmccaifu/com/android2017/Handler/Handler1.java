package zhiwenyan.cmccaifu.com.android2017.Handler;

import android.util.Log;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class Handler1 {

    private MessageQueue1 mQueue;

    public void sendMessage(Message1 msg) {
        sendMessageDelayed(msg,0);
    }
    public final boolean sendMessageDelayed(Message1 msg, long delayMillis)
    {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return sendMessageAtTime(msg, System.currentTimeMillis() + delayMillis);
    }


    public boolean sendMessageAtTime(Message1 msg, long uptimeMillis) {
        MessageQueue1 queue = mQueue;
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            Log.w("Looper", e.getMessage(), e);
            return false;
        }
        return enqueueMessage(queue, msg, uptimeMillis);
    }

    private boolean enqueueMessage(MessageQueue1 queue, Message1 msg, long uptimeMillis) {
//        msg.target = this;
//        if (mAsynchronous) {
//            msg.setAsynchronous(true);
//        }
        return queue.enqueueMessage(msg, uptimeMillis);
    }

    public void handlerMessage(Message1 msg) {

    }

}
