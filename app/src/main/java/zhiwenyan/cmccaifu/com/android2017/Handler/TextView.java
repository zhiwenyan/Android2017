package zhiwenyan.cmccaifu.com.android2017.Handler;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class TextView {
    private Thread mCurrentThread;

    public TextView() {
        mCurrentThread = Thread.currentThread();
    }

    public void setText(CharSequence text) {
        checkThread();
        System.out.println(text.toString());
    }

    private void checkThread() {
        if (mCurrentThread != Thread.currentThread()) {
            throw new RuntimeException(
                    "Only the original thread that created a view hierarchy can touch its views.");
        }
    }
}
