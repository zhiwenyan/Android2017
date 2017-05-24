package zhiwenyan.cmccaifu.com.android2017.materialdesign;

import android.support.v7.widget.Toolbar;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.utils.StatusBarUtil;

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
        StatusBarUtil.setImmersiveStatusBar(this);
//        StatusBarUtil.setImmersiveStatusBarToolbar(toolbar,this);

    }
}
