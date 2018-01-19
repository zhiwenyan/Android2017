package zhiwenyan.cmccaifu.com.android2017.view.multiScreenView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description: 适配器模式 对象适配
 * Data：12/29/2017-3:26 PM
 *
 * @author: yanzhiwen
 */
public class ListScreenMenuAdapter extends BaseMenuAdapter {
    private String[] mItems = {"品牌", "价格", "类型", "更多"};
    private Context mContext;

    public ListScreenMenuAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        TextView tabView = (TextView) LayoutInflater.from(mContext)
                .inflate(R.layout.list_menu_item, parent, false);
        tabView.setText(mItems[position]);
        tabView.setTextColor(Color.BLACK);
        return tabView;
    }

    @Override
    public View getMenuView(int position, ViewGroup parent) {
        //菜单的内容
        TextView menuView = (TextView) LayoutInflater.from(mContext)
                .inflate(R.layout.list_menu_content_item, parent, false);
        menuView.setText(mItems[position]);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭菜单,需要调用ListDataScreenView的关闭菜单的方法
                //方式一：可以利用事件监听
                //方法二：可以让Adapter去持有ListDataScreenView对象，把对象传入构造函数中，赋值
                //方式三：观察者设计模式（观察者和被观察者，注册订阅的概念）（listView的notifyDataChanged()）.
                closeMenu();
            }
        });
        return menuView;
    }

    @Override
    public void openMenu(int position, View tabView) {
        //直接强转
        ((TextView) tabView).setTextColor(Color.RED);
    }

    @Override
    public void closeMenu(View tabView) {
        ((TextView) tabView).setTextColor(Color.BLACK);
    }
}
