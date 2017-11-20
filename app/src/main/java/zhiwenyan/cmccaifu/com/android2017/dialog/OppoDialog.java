package zhiwenyan.cmccaifu.com.android2017.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：11/17/2017-10:32 AM
 * Author: yanzhiwen
 */
public class OppoDialog extends Dialog {
    public OppoDialog(@NonNull Context context) {
        super(context);
    }

    public OppoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.oppo_dialog);
    }
}
