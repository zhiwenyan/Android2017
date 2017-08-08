package zhiwenyan.cmccaifu.com.android2017.ViewGroup.Drag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class VerticalDragActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vertical_drag;
    }

    @Override
    protected void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Character> list = new ArrayList<>();
        for (char i = 'A'; i < 'Z'; i++) {
            list.add(i);
        }
        DragAdapter adapter = new DragAdapter(this, list, R.layout.drag_item_layout);
        mRecyclerView.setAdapter(adapter);
    }
}
