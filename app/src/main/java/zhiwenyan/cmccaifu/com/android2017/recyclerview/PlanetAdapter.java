package zhiwenyan.cmccaifu.com.android2017.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by admin on 2017/12/7.
 */

public class PlanetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Leaderboard> mList;
    private LayoutInflater mLayoutInflater;

    public PlanetAdapter(Context context, List<Leaderboard> mlist) {
        this.mContext = context;
        this.mList = mlist;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 3) {
            return R.layout.lv_img_ranking;
        }
        return R.layout.lv_ranking;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == R.layout.lv_img_ranking) {
            itemView = mLayoutInflater.inflate(viewType, parent, false);
            return new Holder(itemView);
        }
        itemView = mLayoutInflater.inflate(viewType, parent, false);
        return new ViewHolder_one(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Leaderboard leaderboard = mList.get(position);
            if (position == 0) {
                ((Holder) holder).image.setImageResource(R.mipmap.one);
            }
            if (position == 1) {
                ((Holder) holder).image.setImageResource(R.mipmap.two);
            }
            if (position == 2) {
                ((Holder) holder).image.setImageResource(R.mipmap.three);

            }
            ((Holder) holder).search.setText(leaderboard.getRanking_search());
            ((Holder) holder).water.setText(leaderboard.getRanking_water());
            ((Holder) holder).vitality.setText(leaderboard.getRanking_vitality());
        } else {
            Leaderboard leaderboard = mList.get(position);
            ((ViewHolder_one) holder).search.setText(leaderboard.getRanking_search());
            ((ViewHolder_one) holder).water.setText(leaderboard.getRanking_water());
            ((ViewHolder_one) holder).vitality.setText(leaderboard.getRanking_vitality());
        }
    }


    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView search;
        private TextView water;
        private TextView vitality;

        public Holder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ranking_img_img);
            search = itemView.findViewById(R.id.ranking_search);
            water = itemView.findViewById(R.id.ranking_water);
            vitality = itemView.findViewById(R.id.ranking_vitality);

        }
    }

    public class ViewHolder_one extends RecyclerView.ViewHolder {
        private TextView search;
        private TextView water;
        private TextView vitality;

        public ViewHolder_one(View itemView) {
            super(itemView);
            search = itemView.findViewById(R.id.ranking_search);
            water = itemView.findViewById(R.id.ranking_water);
            vitality = itemView.findViewById(R.id.ranking_vitality);

        }
    }
}
