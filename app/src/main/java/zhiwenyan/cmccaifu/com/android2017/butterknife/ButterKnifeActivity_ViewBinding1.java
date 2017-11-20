package zhiwenyan.cmccaifu.com.android2017.butterknife;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：11/16/2017-2:02 PM
 * Author: yanzhiwen
 */
public class ButterKnifeActivity_ViewBinding1 {
    private ButterKnifeActivity target;

    @UiThread
    public ButterKnifeActivity_ViewBinding1(ButterKnifeActivity target) {
        this(target, target.getWindow().getDecorView());
    }

    @UiThread
    public ButterKnifeActivity_ViewBinding1(ButterKnifeActivity target, View source) {
        this.target = target;

        target.tv1 = (TextView) target.findViewById(R.id.tv1);
        target.tv2 = (TextView) target.findViewById(R.id.tv2);
    }

    @CallSuper
    public void unbind() {
        ButterKnifeActivity target = this.target;
        if (target == null) throw new IllegalStateException("Bindings already cleared.");
        this.target = null;

        target.tv1 = null;
        target.tv2 = null;
    }
}
