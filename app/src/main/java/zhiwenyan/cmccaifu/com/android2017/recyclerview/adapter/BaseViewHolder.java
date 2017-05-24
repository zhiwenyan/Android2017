package zhiwenyan.cmccaifu.com.android2017.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 4/18/17.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private Context context;
    private View convertView;

    public BaseViewHolder(Context context, View view) {
        super(view);
        this.context = context;
        this.views = new SparseArray<>();
        convertView = view;
    }

    private <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, CharSequence value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    public BaseViewHolder setImageView(int viewId) {
        ImageView imageView = retrieveView(viewId);
        imageView.setImageResource(R.mipmap.animation_img1);
        return this;
    }

}
