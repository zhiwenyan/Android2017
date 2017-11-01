package zhiwenyan.cmccaifu.com.android2017.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class SwapeBackActivity extends BaseActivity {

    @BindView(R.id.swipeBackLayout)
    SwipeBackLayout mSwipeBackLayout;
    @BindView(R.id.text)
    TextView mText;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_swape_back;
    }

    @Override
    protected void init() {
        doSetToolBarTitle("侧滑返回Activity");
        mSwipeBackLayout.setCallBackListener(new SwipeBackLayout.callBackListener() {
            @Override
            public void finish() {
                SwapeBackActivity.this.finish();
            }
        });
    }


    @OnClick(R.id.text)
    public void onClick() {
        Toast.makeText(this,mText.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}
