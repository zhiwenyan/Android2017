package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.adapter.ListContentAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.ItemClickListener;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration.DefaultItemDecoration;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.wrap.WrapRecyclerView;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class HeadAndFooterActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    WrapRecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private View mHeaderView;
    private View mFooterView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head_and_footer;
    }

    @Override
    protected void init() {
        doSetToolBarTitle("添加头部和尾部");
        for (int i = 0; i < 20; i++) {
            mList.add("content---" + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        ListContentAdapter adapter = new ListContentAdapter(this, mList, R.layout.list_content_item);
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.list_header_item, mRecyclerView, false);
        mFooterView = LayoutInflater.from(this).inflate(R.layout.list_footer_item, mRecyclerView, false);
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(this, R.drawable.default_item));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addHeaderView(mHeaderView);
        mRecyclerView.addFooterView(mFooterView);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(HeadAndFooterActivity.this, "你单击了第：" + position + "个", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.footer_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.header:
                mHeaderView = LayoutInflater.from(this).inflate(R.layout.list_header_item, mRecyclerView, false);
                mRecyclerView.addHeaderView(mHeaderView);
                break;
            case R.id.footer:
                mFooterView = LayoutInflater.from(this).inflate(R.layout.list_footer_item, mRecyclerView, false);
                mRecyclerView.addFooterView(mFooterView);
                break;
            case R.id.removeheader:
                mRecyclerView.removeHeaderView(mHeaderView);
                break;
            case R.id.removefooter:
                mRecyclerView.removeFooterView(mFooterView);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
