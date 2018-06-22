package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonRecycleAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonViewHolder;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.http.RetrofitClient;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.http.User;
import zhiwenyan.cmccaifu.com.android2017.utils.DensityUtil;

public class StaggeredGridActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRv;
    private SwipeRefreshLayout mSwipeRefresh;
    private List<Integer> mIntegers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid);
        mRv = findViewById(R.id.rv);
        mSwipeRefresh = findViewById(R.id.swipe);
        initRecyclerView();
        onRequestData();
        mSwipeRefresh.setOnRefreshListener(this);

    }

    private void onRequestData() {
        mSwipeRefresh.setRefreshing(true);
        RetrofitClient.getServiceApi().getUsers(1, 20)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        if (response.body() != null) {
                            setData(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {

                    }
                });

    }

    private void initRecyclerView() {
    }

    public void setData(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            int height = ( int ) (Math.random() * 60 + 100);
            mIntegers.add(height);

        }
        mSwipeRefresh.setRefreshing(false);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//       layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRv.setLayoutManager(layoutManager);
        StaggeredGridAdapter staggeredGridAdapter = new StaggeredGridAdapter(this, users, R.layout.recycle_iv);
        mRv.setAdapter(staggeredGridAdapter);
//        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                layoutManager.invalidateSpanAssignments();
//            }
//        });


//        StaggeredGridAdapter staggeredGridAdapter = new StaggeredGridAdapter(this, users, R.layout.recycle_iv);
//        mRv.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
//        mRv.setAdapter(staggeredGridAdapter);

    }

    @Override
    public void onRefresh() {
        onRequestData();
    }

    private class StaggeredGridAdapter extends CommonRecycleAdapter<User> {
        RequestManager mRequestManager;
        private SparseArray<Integer> mSparseArray = new SparseArray<>();

        public StaggeredGridAdapter(Context context, List<User> users, int layoutId) {
            super(context, users, layoutId);
            mRequestManager = Glide.with(StaggeredGridActivity.this);


        }

        @Override
        protected void convert(CommonViewHolder holder, User user, int position) {
            ImageView iv = holder.getView(R.id.iv);
            ViewGroup.LayoutParams lp = iv.getLayoutParams();
            lp.height = DensityUtil.dip2px(StaggeredGridActivity.this, mIntegers.get(position));
            iv.setLayoutParams(lp);
            DrawableTypeRequest<String> drawableTypeRequest;
            drawableTypeRequest = mRequestManager.load(user.getAvatarUrl());
            drawableTypeRequest.into(iv);

        }
    }
}
