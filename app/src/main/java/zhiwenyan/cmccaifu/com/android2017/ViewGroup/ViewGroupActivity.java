package zhiwenyan.cmccaifu.com.android2017.ViewGroup;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.Drag.VerticalDragActivity;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.Scroller.ScrollerActivity;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.refresh.SwipeRefreshActivity;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.refuse.RefuseActivity;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.slideMenu.SlideMenuActivity;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.tagLayout.TagLayoutActivity;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ViewGroupActivity extends BaseActivity {

    @BindView(R.id.ViewDragHelperBtn)
    Button mViewDragHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_group;
    }

    @Override
    protected void doSetToolBarTitle(String title) {
        super.doSetToolBarTitle("ViewGroup");
    }

    @OnClick({R.id.ViewDragHelperBtn, R.id.back, R.id.tag, R.id.slide, R.id.drag, R.id.vg, R.id.Scroller, R.id.refuse,R.id.refresh})
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
            case R.id.drag:
                startActivity(new Intent(this, VerticalDragActivity.class));
                break;
            case R.id.vg:
                startActivity(new Intent(this, zhiwenyan.cmccaifu.com.android2017.ViewGroup.touch.ViewGroupActivity.class));
                break;
            case R.id.Scroller:
                startActivity(new Intent(this, ScrollerActivity.class));
                break;
            case R.id.refuse:
                startActivity(new Intent(this, RefuseActivity.class));
                break;
            case R.id.refresh:
                startActivity(new Intent(this, SwipeRefreshActivity.class));

                break;

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
