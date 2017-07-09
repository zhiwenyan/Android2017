package zhiwenyan.cmccaifu.com.android2017.ViewGroup.Drag;

import android.content.Context;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonRecycleAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonViewHolder;

/**
 * Created by zhiwenyan on 09/07/2017.
 */

public class DragAdapter extends CommonRecycleAdapter<Character> {

    public DragAdapter(Context context, List<Character> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    @Override
    protected void convert(CommonViewHolder holder, Character character, int position) {
        holder.setText(R.id.tv, character.toString());
    }
}
