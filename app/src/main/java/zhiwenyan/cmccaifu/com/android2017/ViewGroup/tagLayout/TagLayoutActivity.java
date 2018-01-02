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
        mItems.add("11");
        mItems.add("22222");
        mItems.add("333333");
        mItems.add("88");
        mItems.add("66666");
        mItems.add("99999");
        mItems.add("888888888");
        mTagLayout = (zhiwenyan.cmccaifu.com.android2017.ViewGroup.TagLayout1.TagLayout) findViewById(R.id.tag);
        mTagLayout.setAdapter(new BaseAdapter() {
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
        });
    }
}