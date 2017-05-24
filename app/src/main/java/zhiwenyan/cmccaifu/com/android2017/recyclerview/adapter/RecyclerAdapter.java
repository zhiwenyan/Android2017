package zhiwenyan.cmccaifu.com.android2017.recyclerview.adapter;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.model.item;


public class RecyclerAdapter extends BaseRecyclerAdapter<item> {
    public RecyclerAdapter(List<item> list,int resid) {
        super(list,resid);
    }


    @Override
    public void setItemViewValue(ItemViewHolder holder, item item) {
        super.setItemViewValue(holder, item);
        holder.setText(R.id.item, item.getName());
    }

}
