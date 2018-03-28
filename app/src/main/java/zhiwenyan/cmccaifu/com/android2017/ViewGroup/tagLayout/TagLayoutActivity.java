package zhiwenyan.cmccaifu.com.android2017.ViewGroup.tagLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.TagLayout1.BaseAdapter;

public class TagLayoutActivity extends AppCompatActivity {
    private zhiwenyan.cmccaifu.com.android2017.ViewGroup.TagLayout1.TagLayout mTagLayout;
    private List<String> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_layout);
        mTagLayout = findViewById(R.id.tag);
        mItems.add("11");
        mItems.add("22222");
        mItems.add("333333");
        mItems.add("8998");
        mItems.add("66666");
        mItems.add("99999");
        mItems.add("888888888");
        mItems.add("11");
        mItems.add("22222");
        mItems.add("333333");
        mItems.add("88999");
        mItems.add("66666");
        mItems.add("99999");
        mItems.add("888888");
        //设置TAG用Adapter设计模式
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            protected int getCount() {
                return mItems.size();
            }

            @Override
            protected View getView(int position, ViewGroup parent) {
                TextView tv = (TextView) LayoutInflater.from(TagLayoutActivity.this)
                        .inflate(R.layout.item_tag, parent, false);
                tv.setText(mItems.get(position));
                return tv;
            }
        };
        mTagLayout.setAdapter(adapter);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) LayoutInflater.from(TagLayoutActivity.this)
                        .inflate(R.layout.item_tag, mTagLayout, false);
                mItems.add("123456");
                tv.setText("123456");
                adapter.addView(tv);
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItems.size() >= 0) {
                    mItems.remove(mItems.size() - 1);
                    adapter.removeView(mItems.size() - 1);
                }
            }
        });

    }
}