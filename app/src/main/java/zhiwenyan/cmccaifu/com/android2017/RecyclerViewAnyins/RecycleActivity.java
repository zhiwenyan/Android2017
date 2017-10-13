package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class RecycleActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycle;
    }

    @OnClick({R.id.basicBtn, R.id.adapterBtn, R.id.headAndFooterBtn,R.id.dragBtn,R.id.sectionItemDecoration})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.basicBtn:
                startActivity(new Intent(this, BasicUseActivity.class));
                break;
            case R.id.adapterBtn:
                startActivity(new Intent(this, CommonAdapterActivity.class));
                break;
            case R.id.headAndFooterBtn:
                startActivity(new Intent(this, HeadAndFooterActivity.class));
                break;
            case R.id.dragBtn:
                startActivity(new Intent(this,ItemDragActivity.class));
                break;
            case R.id.sectionItemDecoration:
                startActivity(new Intent(this,SectionItemDecorationActivity.class));

                break;
        }
    }
}
