package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.wrap;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by zhiwenyan on 5/25/17.
 * <p>
 * 添加头部和尾部 移除头部和尾部 装饰者设计模式
 */

public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //头部和尾部的集合用SparseArray
    private RecyclerView.Adapter mAdapter;
    private SparseArray<View> mHeaders, mFooters;
    private static int BASE_HEADER_KEY = 10000;
    private static int BASE_FOOTER_KEY = 20000;


    public WrapRecyclerAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        mHeaders = new SparseArray<>();
        mFooters = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaders.indexOfKey(viewType) >= 0) {
            //头部
            return onCreateHeaderViewHolder(mHeaders.get(viewType));
        } else if (mFooters.indexOfKey(viewType) >= 0) {
            //底部
            return onCreateFooterViewHolder(mFooters.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * @param itemView
     * @return
     */
    private RecyclerView.ViewHolder onCreateHeaderViewHolder(View itemView) {
        return new RecyclerView.ViewHolder(itemView) {

        };
    }

    /**
     * @param itemView
     * @return
     */
    private RecyclerView.ViewHolder onCreateFooterViewHolder(View itemView) {
        return new RecyclerView.ViewHolder(itemView) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int numHeaders = mHeaders.size();
        if (position < numHeaders) {
            return;
        }
        //adapter
        int adjPosition = position - numHeaders;
        int adapterCount;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, position - mHeaders.size());

            }
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mHeaders.size() + mFooters.size();
    }

    @Override
    public int getItemViewType(int position) {
        //header
        int numHeaders = mHeaders.size();
        if (position < numHeaders) {
            return mHeaders.keyAt(position);
        }
        //adapter
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        //footer
        int footerPosition = adjPosition - adapterCount;
        return mFooters.keyAt(footerPosition);
    }

    /**
     * 添加头部信息
     *
     * @param view
     */
    public void addHeaderView(View view) {
        if (mHeaders.indexOfValue(view) == -1) {
            mHeaders.put(BASE_HEADER_KEY++, view);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加尾部信息
     *
     * @param view
     */
    public void addFooterView(View view) {
        if (mFooters.indexOfValue(view) == -1) {
            mFooters.put(BASE_FOOTER_KEY++, view);
            notifyDataSetChanged();

        }
    }

    /**
     * 移除头部信息
     *
     * @param view
     */
    public void removeHeaderView(View view) {
        if (mHeaders.indexOfValue(view) >= 0) {
            mHeaders.removeAt(mHeaders.indexOfValue(view));
            notifyDataSetChanged();
        }
    }

    /**
     * 移除尾部信息
     *
     * @param view
     */
    public void removeFooterView(View view) {
        if (mFooters.indexOfValue(view) >= 0) {
            mFooters.removeAt(mFooters.indexOfValue(view));
            notifyDataSetChanged();
        }
    }
}
