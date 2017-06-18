package zhiwenyan.cmccaifu.com.android2017.ViewGroup;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import butterknife.InjectView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.slideMenu.SlideMenuActivity;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.tagLayout.TagLayoutActivity;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ViewGroupActivity extends BaseActivity {

    @InjectView(R.id.ViewDragHelperBtn)
    Button mViewDragHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_group;
    }

    @Override
    protected void doSetToolBarTitle(String title) {
        super.doSetToolBarTitle("ViewGroup");
    }

    @OnClick({R.id.ViewDragHelperBtn, R.id.back, R.id.tag,R.id.slide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ViewDragHelperBtn:
                startActivity(new Intent(this, ViewDragHelperActivity.class));
                break;
            case R.id.back:
                startActivity(new Intent(this, SwapeBackActivity.class));
                break;
            case R.id.tag:
                startActivity(new Intent(this, TagLayoutActivity.class));
                break;
            case R.id.slide:
                startActivity(new Intent(this, SlideMenuActivity.class));
                break;
        }
    }
}
