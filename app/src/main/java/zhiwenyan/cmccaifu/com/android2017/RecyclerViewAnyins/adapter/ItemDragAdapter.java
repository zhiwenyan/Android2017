package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.adapter;

import android.content.Context;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonRecycleAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonViewHolder;

/**
 * Created by zhiwenyan on 5/26/17.
 */

public class ItemDragAdapter extends CommonRecycleAdapter<String> {
    public ItemDragAdapter(Context context, List<String> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    @Override
    protected void convert(CommonViewHolder holder, String s, int position) {
        holder.setText(R.id.chat_text, s);
    }
}
