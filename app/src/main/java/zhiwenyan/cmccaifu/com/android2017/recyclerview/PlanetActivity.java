package zhiwenyan.cmccaifu.com.android2017.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

public class PlanetActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Leaderboard> mLeaderboards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
      //  initData();
    }

//    private void initData() {
//        for (int i = 0; i < 20; i++) {
//            Leaderboard leaderboard = new Leaderboard(i + 1, "hh", "hhh", "hhh");
//            mLeaderboards.add(leaderboard);
//        }
//        PlanetAdapter planetAdapter = new PlanetAdapter(this, mLeaderboards, new MultipleTypeSupport<Leaderboard>() {
//            @Override
//            public int getLayoutId(Leaderboard item) {
//                if (item.getRanking() <= 3) {
//                    return R.layout.lv_img_ranking;
//                }
//                return R.layout.lv_ranking;
//
//            }
//        });
//        mRecyclerView.setAdapter(planetAdapter);
//    }
//
//    private class PlanetAdapter extends CommonRecycleAdapter<Leaderboard> {
//
//        public PlanetAdapter(Context context, List<Leaderboard> datas, MultipleTypeSupport typeSupport) {
//            super(context, datas, typeSupport);
//        }
//
//        @Override
//        protected void convert(CommonViewHolder holder, Leaderboard leaderboard, int position) {
//            if (position == 0) {
//                holder.setImageResoucrce(R.id.ranking_img_img, R.mipmap.one);
//            }
//            if (position == 1) {
//                holder.setImageResoucrce(R.id.ranking_img_img, R.mipmap.two);
//            }
//            if (position == 2) {
//                holder.setImageResoucrce(R.id.ranking_img_img, R.mipmap.three);
//            }
//            if (position >= 3) {
//                holder.setText(R.id.ranking_search, leaderboard.getRanking_search())
//                        .setText(R.id.ranking_water, leaderboard.getRanking_water())
//                        .setText(R.id.ranking_vitality, leaderboard.getRanking_vitality())
//                        .setText(R.id.rankingTv, leaderboard.getRanking() + "");
//            }
//        }
//    }
}
