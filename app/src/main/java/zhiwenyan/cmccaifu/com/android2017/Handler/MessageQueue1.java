package zhiwenyan.cmccaifu.com.android2017.Handler;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

class MessageQueue1 {
    public boolean enqueueMessage(Message1 msg, long uptimeMillis) {
//        if (msg.target == null) {
//            throw new IllegalArgumentException("Message must have a target.");
//        }
//        if (msg.isInUse()) {
//            throw new IllegalStateException(msg + " This message is already in use.");
//        }
//
//        synchronized (this) {
//            if (mQuitting) {
//                IllegalStateException e = new IllegalStateException(
//                        msg.target + " sending message to a Handler on a dead thread");
//                Log.w(TAG, e.getMessage(), e);
//                msg.recycle();
//                return false;
//            }
//
//            msg.markInUse();
//            msg.when = when;
//            Message p = mMessages;
//            boolean needWake;
//            if (p == null || when == 0 || when < p.when) {
//                // New head, wake up the event queue if blocked.
//                msg.next = p;
//                mMessages = msg;
//                needWake = mBlocked;
//            } else {
//                // Inserted within the middle of the queue.  Usually we don't have to wake
//                // up the event queue unless there is a barrier at the head of the queue
//                // and the message is the earliest asynchronous message in the queue.
//                needWake = mBlocked && p.target == null && msg.isAsynchronous();
//                Message prev;
//                for (; ; ) {
//                    prev = p;
//                    p = p.next;
//                    if (p == null || when < p.when) {
//                        break;
//                    }
//                    if (needWake && p.isAsynchronous()) {
//                        needWake = false;
//                    }
//                }
//                msg.next = p; // invariant: p == prev.next
//                prev.next = msg;
//            }
//
//            // We can assume mPtr != 0 because mQuitting is false.
//            if (needWake) {
//                nativeWake(mPtr);
//            }
//        }
        return false;
    }
}
