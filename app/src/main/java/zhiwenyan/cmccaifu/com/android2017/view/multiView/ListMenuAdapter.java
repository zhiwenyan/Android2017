package zhiwenyan.cmccaifu.com.android2017.view.multiView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/8/15.
 */

public class ListMenuAdapter extends BaseMenuAdapter {
    private String[] mItems = {"类型", "价格", "品牌", "更多"};
    private Context mContext;

    public ListMenuAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public View getTabView(int position, ViewGroup parent) {
        //真正开发过程中，不同的位置显示的布局不一样
        TextView tabView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.list_menu_item, parent, false);
        tabView.setText(mItems[position]);
        return tabView;
    }

    /**
     * 不同位置显示的布局不同
     *
     * @param position
     * @param parent
     * @return
     */
    @Override
    public View getMenuView(int position, ViewGroup parent) {
        TextView menuView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.list_menu_content_item, parent, false);
        menuView.setText(mItems[position]);
        return menuView;
    }
}
