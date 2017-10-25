package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.Model.Movies;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonRecycleAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonViewHolder;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration.GroupListener;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration.SectionItemDecoration;

public class SectionItemDecorationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private String url = "http://api.meituan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost" +
            "=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android" +
            "&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6A" +
            "AB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=146" +
            "3704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi2" +
            "0CGXQPPZvhCU3%2FkzdE%3D";

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_item_decoration);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        getMovies();


    }

    private void getMovies() {
        Request.Builder request = new Request.Builder();
        request.get().url(url);
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request.build()).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Movies movies = new Gson().fromJson(result, Movies.class);
                final List<Movies.DataBean.ComingBean> mLists = movies.getData().getComing();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showData(mLists);
                    }
                });
            }
        });
    }

    private void showData(final List<Movies.DataBean.ComingBean> lists) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new SectionItemDecoration(this, R.drawable.item_decration, R.drawable.default_item, lists, new GroupListener() {
            @Override
            public String getGroupName(int position) {
                return lists.get(position).getComingTitle();
            }

            @Override
            public View getGroupView(int position) {
                View view = getLayoutInflater().inflate(R.layout.section_layout, null, false);
                return view;
            }
        }));
        MovieAdapter movieAdapter = new MovieAdapter(this, lists, R.layout.section_layout);
        mRecyclerView.setAdapter(movieAdapter);
    }

    private class MovieAdapter extends CommonRecycleAdapter<Movies.DataBean.ComingBean> {

        public MovieAdapter(Context context, List<Movies.DataBean.ComingBean> mDatas, int layoutId) {
            super(context, mDatas, layoutId);
        }

        @Override
        protected void convert(CommonViewHolder holder, Movies.DataBean.ComingBean movie, int position) {
            ImageView imageView = holder.getView(R.id.movieImg);
            Glide.with(SectionItemDecorationActivity.this.getApplicationContext()).load(movie.getImg()).into(imageView);
            holder.setText(R.id.nameTv, movie.getNm()).setText(R.id.catTv, movie.getCat())
                    .setText(R.id.actorTv, movie.getDesc());

        }
    }
}
