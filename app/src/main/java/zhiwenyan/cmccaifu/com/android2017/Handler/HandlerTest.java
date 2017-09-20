package zhiwenyan.cmccaifu.com.android2017.Handler;

/**
 * Created by yanzhiwen on 2017/9/19.
 */

public class HandlerTest {
    public static void main(String[] args) {

        Looper.prepareMainLooper();

        ActivityThread thread = new ActivityThread();
        thread.attach(false);
//
//        if (sMainThreadHandler == null) {
//            sMainThreadHandler = thread.getHandler();
//        }
//
//        if (false) {
//            Looper.myLooper().setMessageLogging(new
//                    LogPrinter(Log.DEBUG, "ActivityThread"));
//        }
//
//        // End of event ActivityThreadMain.
//        Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
//        Looper.loop();
//
//        throw new RuntimeException("Main thread loop unexpectedly exited");
//    }
    }
}
