package zhiwenyan.cmccaifu.com.android2017.ViewGroup.event;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import zhiwenyan.cmccaifu.com.android2017.R;

public class ScrollConflictActivity extends AppCompatActivity {

    private BadViewPager mViewPager;
    private List<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict);
        initViews();
        initData(true);
    }

    protected void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mViews = new ArrayList<>();
    }

    @SuppressLint("CheckResult")
    protected void initData(final boolean isListView) {
        //初始化mViews列表
        Flowable.just("view1", "view2", "view3", "view4").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //当前View
                View view;
                if (isListView) {
                    //初始化ListView
                    FixListView listView = new FixListView(ScrollConflictActivity.this);
                    final ArrayList<String> datas = new ArrayList<>();
                    //初始化数据，datas ,data0 ...data49
                    Flowable.range(0, 50).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            datas.add("data" + integer);
                        }
                    });
                    //初始化adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter(ScrollConflictActivity.this,
                            android.R.layout.simple_list_item_1, datas);
                    //设置adapter
                    listView.setAdapter(adapter);
                    //将ListView赋值给当前View
                    view = listView;
                } else {
                    //初始化TextView
                    Button textView = new Button(ScrollConflictActivity.this);
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(s);
                    textView.setClickable(true);
                    //将TextView赋值给当前View
                    view = textView;
                }
                //将当前View添加到ViewPager的ViewList中去
                mViews.add(view);
            }
        });
        //设置ViewPager的Adapter
        mViewPager.setAdapter(new BasePagerAdapter(mViews));
    }
}
