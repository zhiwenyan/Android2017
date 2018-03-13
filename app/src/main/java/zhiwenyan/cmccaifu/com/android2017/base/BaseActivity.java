package zhiwenyan.cmccaifu.com.android2017.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initToolBar();


        init();


    }

    protected void initToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            doSetToolBarTitle("");
        }
    }

    protected void init() {

    }

    protected void doSetToolBarTitle(String title) {
        getSupportActionBar().setTitle(title);

    }

    protected abstract int getLayoutId();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
