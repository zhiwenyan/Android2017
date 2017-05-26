package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.adapter.RecyclerAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration.DefaultItemDecoration;

public class BasicUseActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_use);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(this, R.drawable.default_item));
        RecyclerAdapter adapter = new RecyclerAdapter(mList);
        mRecyclerView.setAdapter(adapter);

    }


    private void initData() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            mList.add(String.valueOf(ch));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                //  mRecyclerView.addItemDecoration(new LinearLayoutItemDecoration(this, R.drawable.item_decration));
                break;
            case R.id.grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
                //    mRecyclerView.addItemDecoration(new GridLayoutItemDecoration(this, R.drawable.item_decration));
                break;
            case R.id.StaggeredGrid:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
