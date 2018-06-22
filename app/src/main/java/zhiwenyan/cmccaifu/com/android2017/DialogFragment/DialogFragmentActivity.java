package zhiwenyan.cmccaifu.com.android2017.DialogFragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;

public class DialogFragmentActivity extends AppCompatActivity {

    @BindView(R.id.confirmDialogBtn)
    Button mConfirmDialogBtn;
    @BindView(R.id.listDialogBtn)
    Button mListDialogBtn;
    @BindView(R.id.calendarDialogBtn)
    Button mCalendarDialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.confirmDialogBtn, R.id.listDialogBtn, R.id.calendarDialogBtn, R.id.progressDialogBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.progressDialogBtn:
                DialogFragmentHelper.showProgress(getSupportFragmentManager(), "加载中");
                break;
            case R.id.confirmDialogBtn:
                DialogFragmentHelper.showConfirmDialog(getSupportFragmentManager(), "确定退出吗？", (dialog, which) -> {

                }, (dialog, which) -> {

                }, true, null);
                break;
            case R.id.listDialogBtn:
                DialogFragmentHelper.showListDialog(getSupportFragmentManager(), "课程", new String[]{"Java", "Oracle", "Android"}, new IDialogResultListener<Integer>() {
                    @Override
                    public void onDataResult(Integer result) {

                    }
                }, true);
                break;
            case R.id.calendarDialogBtn:
                DialogFragmentHelper.showDateDialog(getSupportFragmentManager(), "时间", new GregorianCalendar(), new IDialogResultListener<Calendar>() {
                    @Override
                    public void onDataResult(Calendar result) {

                    }
                }, true);
                break;
        }
    }
}
