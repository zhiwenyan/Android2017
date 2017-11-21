package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

/**
 * Description:
 * Data：11/21/2017-2:14 PM
 * Author: yanzhiwen
 */
public abstract  class NamedRunnable implements   Runnable  {
    @Override
    public void run() {
        execute();
    }
    public abstract void execute();
}
