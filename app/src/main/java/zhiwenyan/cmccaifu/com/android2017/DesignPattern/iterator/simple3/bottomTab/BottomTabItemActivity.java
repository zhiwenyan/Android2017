package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.bottomTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.iterator.ListIterator;
import zhiwenyan.cmccaifu.com.android2017.R;


/**
 * Created by yanzhiwen on 11/10/2017.
 */

public class BottomTabItemActivity extends AppCompatActivity {
    private TabBottomNavigation mTabBottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mTabBottomNavigation = (TabBottomNavigation) findViewById(R.id.tab_bottom);
//        List<BottomTabItem> items = new ArrayList<>();
        ListIterator<MainBottomTabItem> items = new ListIterator<>();
        items.addItem(new MainBottomTabItem.Builder(this).setText("底部").setIcon(R.drawable.main_tab_item).create());
        items.addItem(new MainBottomTabItem.Builder(this).setText("底部").setIcon(R.drawable.main_tab_item).create());
        items.addItem(new MainBottomTabItem.Builder(this).setText("底部").setIcon(R.drawable.main_tab_item).create());
        items.addItem(new MainBottomTabItem.Builder(this).setText("底部").setIcon(R.drawable.main_tab_item).create());
        /// 还可以写灵活一些什么意思
        // 第一个刚刚看到我报错了
        // 有可能我们不传 List Array[]怎么办法呢？
        mTabBottomNavigation.addTabItem(items);
    }
}
