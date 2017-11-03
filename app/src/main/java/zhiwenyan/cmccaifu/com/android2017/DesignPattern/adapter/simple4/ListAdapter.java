package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by yanzhiwen on 2017/11/3.
 */

public class ListAdapter implements AdapterTarget {
    private List<String> mItems;
    private Context mContext;

    public ListAdapter(List<String> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        android.widget.TextView itemView =
                (android.widget.TextView) LayoutInflater.from(mContext).inflate(R.layout.steven_list_item, parent, false);
        itemView.setText(mItems.get(position));
        return itemView;
    }
}
