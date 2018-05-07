package zhiwenyan.cmccaifu.com.android2017.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by hcDarren on 2017/7/10.
 */

public class BehaviorActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior1);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);

        // 监听 ScrollView 的滚动 等等一些处理

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(BehaviorActivity.this).inflate(R.layout.item_behavior, parent, false);

                // View itemView = View.inflate(BehaviorActivity.this,R.layout.item_behavior,null);
                return new ViewHolder(itemView);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
