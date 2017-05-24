package zhiwenyan.cmccaifu.com.android2017.ViewGroup.tagcloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

public class TagCloudActivity extends AppCompatActivity {
    private List<String> mList;
    private TagCloudLayout mContainer;
    private TagBaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_cloud);
        mContainer = (TagCloudLayout) findViewById(R.id.container);
        mList = new ArrayList<>();
        mList.add("中华人名共和国");
        mList.add("大韩民国");
        mList.add("日本");
        mList.add("朝鲜");
        mList.add("台湾");
        mList.add("香港特别行政区");
        mList.add("澳门特别行政区");
        mAdapter = new TagBaseAdapter(this,mList);
//        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mList.add("东帝汶");
//                mAdapter.notifyDataSetChanged();
//            }
//        });
        mContainer.setAdapter(mAdapter);
        mContainer.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                Toast.makeText(TagCloudActivity.this,mList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
