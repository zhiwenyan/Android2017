package zhiwenyan.cmccaifu.com.android2017.materialdesign;

import android.support.v7.widget.Toolbar;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ScrollingActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scrolling;
    }

    @Override
    protected void init() {
        super.init();
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("协调性布局");
        StatusBarUtil.setStatusBarTranslucent(this);
//        StatusBarUtil.setImmersiveStatusBarToolbar(toolbar,this);

    }
}
