package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhiwenyan.cmccaifu.com.android2017.R;

public class WrapRecyclerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerAdapter RecyclerAdapter = new RecyclerAdapter();
        WrapRecyclerAdapter wrapRecyclerAdapter = new WrapRecyclerAdapter(RecyclerAdapter);
        mRecyclerView.setAdapter(wrapRecyclerAdapter);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(WrapRecyclerActivity.this)
                    .inflate(R.layout.list_content_item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText("position->" + position);
        }

        @Override
        public int getItemCount() {
            return 100;
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
