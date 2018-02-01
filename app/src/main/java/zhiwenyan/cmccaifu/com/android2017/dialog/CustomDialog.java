package zhiwenyan.cmccaifu.com.android2017.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 5/9/17.
 */

public class CustomDialog extends AlertDialog {

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.oppo_dialog);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}
