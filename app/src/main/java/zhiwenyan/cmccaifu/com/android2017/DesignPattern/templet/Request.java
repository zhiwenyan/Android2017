package zhiwenyan.cmccaifu.com.android2017.DesignPattern.templet;

import android.support.annotation.NonNull;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class Request implements Runnable, Comparable<Request> {
    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public int compareTo(@NonNull Request o) {
        return 0;
    }
}
