package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.support.v7.widget.RecyclerView;

import butterknife.InjectView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class JokeListActivity extends BaseActivity {

    @InjectView(R.id.rv)
    RecyclerView mRv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_joke_list;
    }

    @Override
    protected void init() {
        doSetToolBarTitle("git");
    }
}
