package zhiwenyan.cmccaifu.com.android2017.DesignPattern.decker.simple2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhiwenyan on 05/11/2017.
 * <p>
 * 装饰设计模式 RecyclerView.Adapter，对其增加头部和底部
 */

public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //原来的RecyclerView.Adapter 并不支持添加头部和底部
    private RecyclerView.Adapter mRealAdapter;
    private ArrayList<View> mHeaderViews;
    private ArrayList<View> mFooterViews;

    public WrapRecyclerAdapter(RecyclerView.Adapter adapter) {
        mRealAdapter = adapter;
        mRealAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                // super.onChanged();
                notifyDataSetChanged();
            }
        });
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        //Header
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return createHeaderViewHolder(mHeaderViews.get(position));
        }

        // mRealAdapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mRealAdapter != null) {
            adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                //直接传position不兼容多种布局
                return mRealAdapter.onCreateViewHolder(parent, mRealAdapter.getItemViewType(position));
            }
        }

        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return createFooterViewHolder(mFooterViews.get(adjPosition - adapterCount));
    }

    private RecyclerView.ViewHolder createHeaderViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    private RecyclerView.ViewHolder createFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // 这个方法先不写，测试一下
        // 头部和底部是都不需要做处理的，只要 mRealAdapter 要去做处理
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }

        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mRealAdapter != null) {
            adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mRealAdapter.onBindViewHolder(holder, position);
            }
        }
    }


    @Override
    public int getItemCount() {
        //头部的item+底部item+mAdapter的列表的数据
        return mHeaderViews.size() + mFooterViews.size() + mRealAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        //把位置直接作为ViewType
        return position;
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    /**
     * 添加头部View
     *
     * @param headerView
     */
    public void addHeaderView(View headerView) {
        if (!mHeaderViews.contains(headerView)) {
            mHeaderViews.add(headerView);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加底部View
     *
     * @param footerView
     */
    public void addFooterView(View footerView) {
        if (!mFooterViews.contains(footerView)) {
            mFooterViews.add(footerView);
            notifyDataSetChanged();
        }
    }
}
