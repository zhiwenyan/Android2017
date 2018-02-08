package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * Description:
 * Data：2/8/2018-2:20 PM
 *
 * @author: yanzhiwen
 */
public class AndroidSchedulers {

    static final Schedulers MAIN_THREAD;

    static {
        MAIN_THREAD = new MainSchedulers(new Handler(Looper.getMainLooper()));
    }

    public static Schedulers mainThread() {
        return MAIN_THREAD;
    }

    private static class MainSchedulers extends Schedulers {
        private Handler handler;

        public MainSchedulers(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            Message message = Message.obtain(handler, runnable);
            handler.sendMessage(message);
        }
    }
}
