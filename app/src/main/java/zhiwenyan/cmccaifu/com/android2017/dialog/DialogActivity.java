package zhiwenyan.cmccaifu.com.android2017.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.View;

import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class DialogActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }
//121.40.231.176

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                new AlertDialog.Builder(this)
                        .setTitle("title")
                        .setMessage("Message")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.btn2:
//                DialogFragment dialogFragment=new DialogFragment();
                break;
            case R.id.btn3:
                ProgressDialog progressDialog = new ProgressDialog(this);
//                progressDialog.setTitle("Load");
                progressDialog.setMax(100);
                progressDialog.setMessage("Load...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(R.layout.activity_main);
                bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
        }
    }
}
