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
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration.GridLayoutItemDecoration;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration.LinearLayoutItemDecoration;

public class BasicUseActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_use);
        initData();
        mRecyclerView = ( RecyclerView ) findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new LinearLayoutItemDecoration(this, R.drawable.default_item));
        RecyclerAdapter adapter = new RecyclerAdapter(mList);
        mRecyclerView.setAdapter(adapter);
//        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
//        int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
//        int totalCount = adapter.getItemCount();
//        System.out.println("firstVisibleItemPosition=" + firstVisibleItemPosition);  //-1
//        System.out.println("lastVisibleItemPosition=" + lastVisibleItemPosition); //-1
//        System.out.println("totalCount=" + totalCount); //26
//        int firstCompletelyVisibleItemPosition = manager.findFirstCompletelyVisibleItemPosition(); //-1
//        int lastCompletelyVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition(); //-1
//        System.out.println("firstCompletelyVisibleItemPosition=" + firstCompletelyVisibleItemPosition);
//        System.out.println("lastCompletelyVisibleItemPosition=" + lastCompletelyVisibleItemPosition);
//        System.out.println("--------------------------------------------");

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                int firstCompletelyVisibleItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                int lastCompletelyVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
//                System.out.println("firstVisibleItemPosition=" + firstVisibleItemPosition + "---" + mList.get(manager.findFirstVisibleItemPosition()));
                System.out.println("lastVisibleItemPosition=" + lastVisibleItemPosition);
                System.out.println("--------------------------------------------");
                System.out.println("firstCompletelyVisibleItemPosition=" + firstCompletelyVisibleItemPosition);
                System.out.println("lastCompletelyVisibleItemPosition=" + lastCompletelyVisibleItemPosition);
            }
        });
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
                mRecyclerView.addItemDecoration(new LinearLayoutItemDecoration(this, R.drawable.item_decration));
                break;
            case R.id.grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
                mRecyclerView.addItemDecoration(new GridLayoutItemDecoration(this, R.drawable.item_decration));
                break;
            case R.id.StaggeredGrid:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
