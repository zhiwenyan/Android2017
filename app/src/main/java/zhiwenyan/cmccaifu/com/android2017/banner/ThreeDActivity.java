package zhiwenyan.cmccaifu.com.android2017.banner;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.recyclerview.adapter.BaseRecyclerAdapter;

public class ThreeDActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<img> mImgs = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_three_d;
    }

    @Override
    protected void init() {
        addImg();
        mRv.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false));
        mRv.setHasFixedSize(true);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setAdapter(new ThreeDAdapter(mImgs, R.layout.list_thred_d));
        mRv.scrollToPosition(mImgs.size() / 2);
    }

    private void addImg() {
        for (int i = 0; i < 3; i++) {
            mImgs.add(new img());
        }
    }


    class ThreeDAdapter extends BaseRecyclerAdapter<img> {

        public ThreeDAdapter(List<img> list, int resid) {
            super(list, resid);
        }

        @Override
        public void setItemViewValue(BaseRecyclerAdapter.ItemViewHolder holder, img item) {
            super.setItemViewValue(holder, item);
            holder.setImageView(R.id.img);

        }
    }

    class img {
    }
}
