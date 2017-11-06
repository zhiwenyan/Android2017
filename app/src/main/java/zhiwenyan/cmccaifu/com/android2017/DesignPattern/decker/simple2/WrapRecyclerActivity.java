package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

public class WrapRecyclerActivity extends AppCompatActivity {
    private WrapRecyclerView mRecyclerView;
    private List<Integer> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_recycler);
        for (int i = 0; i < 20; i++) {
            mItems.add(i);
        }
        mRecyclerView = (WrapRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerAdapter RecyclerAdapter = new RecyclerAdapter();
        //  WrapRecyclerAdapter wrapRecyclerAdapter = new WrapRecyclerAdapter(RecyclerAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.header_view, mRecyclerView, false);
        mRecyclerView.setAdapter(RecyclerAdapter);
        mRecyclerView.addHeaderView(headView);
        //面向对象六大基本原则在哪里？最少知识原则又在哪里？必须要让ListView那样支持

        //不要代码过度封装

    }

    private class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(WrapRecyclerActivity.this)
                    .inflate(R.layout.list_content_item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.mTextView.setText("position->" + mItems.get(position));
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (android.widget.TextView) itemView.findViewById(R.id.content);
        }

    }
}
